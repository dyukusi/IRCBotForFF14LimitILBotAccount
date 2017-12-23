public class Conf {
    static final int LIMIT_TIME_PER_SHARE = 60 * 10;
    static final String PRIVATE_MESSAGE_LOG_FILE_PATH = "./log/%s_pm.txt";
    static final String CHANNEL_MESSAGE_LOG_FILE_PATH = "./log/%s_%s.txt";

    static class IRC_BOT {
        static final String BOT_NAME = "ShareBot"; // NOTE: 9文字以下
        static final String[] JOIN_CHANNEL_NAMES = {
                "#mana-il-limit", // 下限ILチャンネル
        };

    }

    class DIRECT_MESSAGE {
        static final String PM_MESSAGE_ACCEPTED = "あなたのメッセージは管理者に送信されました。返信をご希望の場合は連絡先を続けてお送り下さい。";

        // Tweet notification
        static final String SHARED_ON_TWITTER          = "あなたの募集がFF14下限ILTwitterBotにより宣伝されました。該当の呟き: " + TWITTER.TWEETED_URL;
        static final String NOT_SHARED_ON_TWITTER      = "前回の募集告知時刻から10分以内のためTwitterでは宣伝されませんでした。前回の告知時刻: %s";
        static final String TWEET_FAILED               = "Twitterでの告知に失敗しました。管理者に問い合わせて下さい。";
        static final String TWEET_LENGTH_EXCEED_FAILED = "ツイートの文字制限を越えたため, あなたの募集はTwitterでは宣伝されませんでした。";
    }

    class TWITTER {
        static final String TWEETED_URL = "https://twitter.com/FF14Limiter/status/%d";

        // TwitterAPI tokens
        static final String ACCESS_TOKEN        = "xxx";
        static final String ACCESS_TOKEN_SECRET = "xxx";
        static final String CONSUMER_KEY        = "xxx";
        static final String CONSUMER_SECRET_KEY = "xxx";
    }
}
