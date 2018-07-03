package twitter2influx;

import java.util.Properties;

import twitter2influx.tweetOutput.factory.TweetOutputFactory;
import twitter2influx.tweetOutput.factory.TweetOutputFactory.TweetOutputType;
import twitter2influx.tweetOutput.TweetOutput;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class Stream {
    private final ConfigurationBuilder cb;

    public Stream(Properties auth) {
        cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(auth.getProperty("ConsumerKey"));
        cb.setOAuthConsumerSecret(auth.getProperty("ConsumerSecret"));
        cb.setOAuthAccessToken(auth.getProperty("AccessToken"));
        cb.setOAuthAccessTokenSecret(auth.getProperty("AccessTokenSecret"));
    }

    public void start(final String[] keywords, TweetOutputType tweetOutputType) {
        final TweetOutput output = TweetOutputFactory.getTweetOutput(tweetOutputType);
        final TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                Tweet tweet = new Tweet(status.getId(),
                        status.getUser().getScreenName(), user.getLocation(),
                        status.getLang(), status.getText(),
                        status.isPossiblySensitive(), status.isRetweet());

                for (String keyword : keywords) {
                    if (!tweet.isRetweet() && "en".equals(tweet.getLanguage()) &&
                            tweet.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                        output.write(tweet, keyword);
                    }
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice sdn) {
                Logger.log(Main.class.getName(), "Deletion notice " + sdn);
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                Logger.log(Main.class.getName(), "Track limit " + i);
            }

            @Override
            public void onScrubGeo(long l, long l1) {
                Logger.log(Main.class.getName(), "Scrub geo " + l + l1);
            }

            @Override
            public void onStallWarning(StallWarning sw) {
                Logger.log(Main.class.getName(), "Stall " + sw.getMessage());
            }

            @Override
            public void onException(Exception ex) {
                Logger.log(Main.class.getName(), "Exception " + ex.getMessage());
            }
        };

        twitterStream.addListener(listener);
        twitterStream.filter(new FilterQuery().track(keywords));
    }
}
