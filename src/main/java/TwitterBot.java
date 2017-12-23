import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterBot {
    private static Twitter twitter;
    public static Twitter getInstance() {
        if (twitter == null) {
            TwitterFactory factory = new TwitterFactory();
            twitter = factory.getInstance();
            twitter.setOAuthConsumer(
                    Conf.TWITTER.CONSUMER_KEY,
                    Conf.TWITTER.CONSUMER_SECRET_KEY
            );
            twitter.setOAuthAccessToken(new AccessToken(
                    Conf.TWITTER.ACCESS_TOKEN,
                    Conf.TWITTER.ACCESS_TOKEN_SECRET
            ));
        }

        return twitter;
    }
}
