package twitter2influx;

import java.util.Date;

public class Logger {

    public static void log(String caller, String message) {
        System.out.println(new Date() + " " + caller + " " + message);
    }

}
