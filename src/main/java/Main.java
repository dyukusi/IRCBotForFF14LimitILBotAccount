public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        // IRCBotの起動
        //bot.connect("ec2-13-115-32-166.ap-northeast-1.compute.amazonaws.com", 6667);
        IrcBot bot = new IrcBot("localhost", 6667);
    }
}
