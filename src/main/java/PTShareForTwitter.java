import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.util.CharacterUtil;
import java.util.HashMap;

public class PTShareForTwitter {
    HashMap<String, Integer> lastSharedTimeMap;
    IrcBot bot;

    PTShareForTwitter(IrcBot bot) {
        this.lastSharedTimeMap = new HashMap<String, Integer>();
        this.bot = bot;
    }

    int share(String senderName, String hostname, String message) {
        int lastSharedAt = lastSharedTimeMap.containsKey(hostname) ? lastSharedTimeMap.get(hostname) : 0;

        // シェア制限の時間内であればTwitterでのシェアを行わない
        if (Util.getUnixTime() < lastSharedAt + Conf.LIMIT_TIME_PER_SHARE) {
            this.directMessageWhenNotShared(senderName, lastSharedAt);
            return 1;
        }

        // Twitterで宣伝
        this.shareByTwitter(senderName, hostname, message);
        return 0;
    }

    void shareByTwitter(String senderName, String hostname, String message) {
        Twitter twitter = TwitterBot.getInstance();

        // ツイート文字数チェック
        if (CharacterUtil.isExceedingLengthLimitation(message)) {
            this.directMessageWhenTweetLengthExceedFailed(senderName);
            return;
        }

        try {
            Status status = twitter.updateStatus(message);
            this.directMessageWhenShared(senderName, status);

            // ツイートに成功したらそのユーザの最終呟き時刻を更新
            lastSharedTimeMap.put(hostname, Util.getUnixTime());
        } catch (TwitterException e) {
            this.directMessageWhenTweetFailed(senderName);
            e.printStackTrace();
        }
    }

    void directMessageWhenTweetLengthExceedFailed(String senderName) {
        this.bot.sendMessage(senderName, Conf.DIRECT_MESSAGE.TWEET_LENGTH_EXCEED_FAILED);
    }

    void directMessageWhenTweetFailed(String senderName) {
        this.bot.sendMessage(senderName, Conf.DIRECT_MESSAGE.TWEET_FAILED);
    }

    void directMessageWhenShared(String senderName, Status status) {
        this.bot.sendMessage(senderName, String.format(Conf.DIRECT_MESSAGE.SHARED_ON_TWITTER, status.getId()));
    }

    void directMessageWhenNotShared(String senderName, int lastSharedAt) {
        String dateStr = Util.getDateStrByUnixTime(lastSharedAt);
        this.bot.sendMessage(senderName, String.format(Conf.DIRECT_MESSAGE.NOT_SHARED_ON_TWITTER, dateStr));
    }
}
