package twitter2influx.tweetOutput;

import twitter2influx.Tweet;
import twitter2influx.Logger;
import twitter2influx.Utils;

import static java.lang.Short.parseShort;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

public class DatabaseTweetOutput implements TweetOutput {

    private BatchPoints batch;
    private InfluxDB influx;
    private short counter;
    private short limit;

    public DatabaseTweetOutput() {
        Properties config = Utils.fileToProperties("influx.properties");
        limit = parseShort(config.getProperty("Limit"));
        counter = 0;

        try {
            influx = InfluxDBFactory.connect(config.getProperty("Url"),
                    config.getProperty("User"),
                    config.getProperty("Pass"));
            batch = BatchPoints.database(config.getProperty("DbName"))
                    .retentionPolicy(config.getProperty("Retention"))
                    .consistency(InfluxDB.ConsistencyLevel.ALL)
                    .build();
        } catch (Exception ex) {
            Logger.log(DatabaseTweetOutput.class.getName(), ex.getMessage());
        }
    }

    @Override
    public void write(Tweet tweet, String series) {
        try {
            batch.point(Point.measurement(series.replace(" ", "_"))
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .addField("id", tweet.getId())
                    .addField("username", tweet.getUsername())
                    .addField("location", tweet.getLocation())
                    .addField("language", tweet.getLanguage())
                    .addField("content", tweet.getContent())
                    .addField("sensitive", tweet.isSensitive())
                    .addField("retweet", tweet.isRetweet())
                    .build());
        } catch (Exception ex) {
            Logger.log(DatabaseTweetOutput.class.getName(), ex.getMessage());
        } finally {
            if(counter++ > limit) {
                try {
                    influx.write(batch);
                } catch (Exception ex) {
                    Logger.log(DatabaseTweetOutput.class.getName(), ex.getMessage());
                }
                batch.getPoints().clear();
                counter = 0;
            }
        }
    }

}
