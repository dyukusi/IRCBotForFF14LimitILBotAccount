import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;

public class IrcBot extends PircBot{
    PTShareForTwitter ptShareForTwitter;
    Logger logger;

    public IrcBot(String hostName, int port) throws IOException, IrcException {
        this.setName(Conf.IRC_BOT.BOT_NAME);
        this.setVerbose(true);

        // 接続
        this.connect(hostName, port);

        for (String channelName : Conf.IRC_BOT.JOIN_CHANNEL_NAMES) {
            this.joinChannel(channelName);
        }

        this.ptShareForTwitter = new PTShareForTwitter(this);
        this.logger = new Logger(this);
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        if (message.matches(".*?#LimitPT.*?")) {
            this.ptShareForTwitter.share(sender, hostname, message);
        }

        // ログ処理
        try {
            this.logger.channelMessage(channel, sender, login, hostname, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrivateMessage(String sender, String login, String hostname, String message) {
        try {
            this.logger.privateMessage(sender, login, hostname, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
