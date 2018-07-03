package twitter2influx;

public class Tweet {

    private long id;
    private String username;
    private String location;
    private String language;
    private String content;
    private boolean sensitive;
    private boolean retweet;

    public Tweet(long id, String username, String location, String language,
            String content, boolean sensitive, boolean retweet) {
        this.id = id;
        this.username = (username == null) ? "undefined" : Utils.stringToSHA256(username);
        this.location = (location == null) ? "undefined" : location;
        this.language = (language == null) ? "undefined" : language;
        this.content = (content == null) ? "undefined" : content;
        this.sensitive = sensitive;
        this.retweet = retweet;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public String getLanguage() {
        return language;
    }

    public String getContent() {
        return content;
    }

    public boolean isSensitive() {
        return sensitive;
    }

    public boolean isRetweet() {
        return retweet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSensitive(boolean sensitive) {
        this.sensitive = sensitive;
    }

    public void setRetweet(boolean retweet) {
        this.retweet = retweet;
    }

    @Override
    public String toString() {
        return "id " + id + '\n'
                + "content " + content + '\n'
                + "username " + username + '\n'
                + "location " + location + '\n';
    }
}
