import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    static public int getUnixTime() {
        return (int)(new Date().getTime() / 1000L);
    }

    static public String getDateStrByUnixTime(int unixtime) {
        Date date = new Date(unixtime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("k'時'mm'分'ss'秒'");
        return sdf.format(date);
    }

    static public PrintWriter getLogger(String logFilePath) throws IOException {
        FileWriter fw = new FileWriter(new File(logFilePath), true);
        BufferedWriter bw = new BufferedWriter(fw);
        return new PrintWriter(bw);
    }

}
