package twitter2influx.tweetOutput;

import twitter2influx.Tweet;

public interface TweetOutput {

    public void write(Tweet tweet, String series);

}
