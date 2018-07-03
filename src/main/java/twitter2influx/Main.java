package twitter2influx;

import twitter2influx.tweetOutput.factory.TweetOutputFactory.TweetOutputType;

public class Main {

    public static void main(String[] args) {
        Logger.log(Main.class.getName(), "Starting stream");

        Stream stream = new Stream(Utils.fileToProperties("twitterAPI.dev.properties"));
        stream.start(Utils.fileToArray("keywords.txt"), TweetOutputType.LOCAL);
    }

}
