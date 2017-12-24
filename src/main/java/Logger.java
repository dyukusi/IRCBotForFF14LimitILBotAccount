import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    IrcBot bot;

    Logger(IrcBot bot) {
        this.bot = bot;
    }

    void channelMessage(String channel, String sender, String login, String hostname, String message) throws IOException {
        String formattedMessage = String.format(
                "%s %s %s : %s",
                this.getLogMessagePrefix(),
                sender,
                hostname,
                message
        ).toString();
        this.log(this.getChannelMsgLogFilePath(channel), formattedMessage);
    }

    void privateMessage(String sender, String login, String hostname, String message) throws IOException {
        String formattedMessage = String.format(
                "%s %s %s : %s",
                this.getLogMessagePrefix(),
                sender,
                hostname,
                message
        ).toString();
        this.log(this.getPMLogFilePath(), formattedMessage);
        this.bot.sendMessage(sender, Conf.DIRECT_MESSAGE.PM_MESSAGE_ACCEPTED);
    }

    void log(String path, String message) throws IOException {
        PrintWriter logger = Util.getLogger(path);
        logger.println(message);
        logger.close();
    }

    String getPMLogFilePath() {
        String prefix = this.getLogFileNamePrefix();
        return String.format(Conf.PRIVATE_MESSAGE_LOG_FILE_PATH, prefix);
    }

    String getChannelMsgLogFilePath(String channel) {
        String prefix = this.getLogFileNamePrefix();
        return String.format(Conf.CHANNEL_MESSAGE_LOG_FILE_PATH, prefix, channel);
    }

    String getLogFileNamePrefix() {
        SimpleDateFormat logFileNamePrefixSDF = new SimpleDateFormat("yyyyMMdd");
        return logFileNamePrefixSDF.format(new Date()).toString();
    }

    String getLogMessagePrefix() {
        SimpleDateFormat logMsgPrefixSDF = new SimpleDateFormat("yyyy MM/dd HH:mm:ss");
        return logMsgPrefixSDF.format(new Date()).toString();
    }

}
