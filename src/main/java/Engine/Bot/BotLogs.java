package Engine.Bot;
import org.apache.logging.log4j.LogManager;

public class BotLogs {
    public static String LOGS_PATH= "test-outputs/Logs";
    public static void info (String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace() [2].toString()).info(message);
    }
    public static void error (String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace() [2].toString()).error(message);
    }

}
