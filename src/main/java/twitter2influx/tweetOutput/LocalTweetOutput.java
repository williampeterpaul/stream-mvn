package twitter2influx.tweetOutput;

import twitter2influx.Tweet;

public class LocalTweetOutput implements TweetOutput {

    @Override
    public void write(Tweet tweet, String series) {
        System.out.println(series + "\n" + tweet);
    }

}
