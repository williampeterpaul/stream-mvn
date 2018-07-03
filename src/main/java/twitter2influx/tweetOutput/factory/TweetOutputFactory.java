package twitter2influx.tweetOutput.factory;

import twitter2influx.tweetOutput.DatabaseTweetOutput;
import twitter2influx.tweetOutput.LocalTweetOutput;
import twitter2influx.tweetOutput.TweetOutput;

public class TweetOutputFactory {

    private TweetOutputFactory() {

    }

    public static TweetOutput getTweetOutput(TweetOutputType tweetOutputType) {
        TweetOutput tweetOutput;

        switch (tweetOutputType) {
            case DATABASE:
                tweetOutput = new DatabaseTweetOutput();
                break;
            default:
                tweetOutput = new LocalTweetOutput();
        }
        return tweetOutput;
    }

    public enum TweetOutputType {
        DATABASE, LOCAL;
    }
}
