package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.Status;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message) {
        String caller = getCaller();
        logger.info(caller+" - "+message);                          // → Log4j2
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().info(message); // → ExtentReports
        }
    }

    public static void pass(String message) {
        String caller = getCaller();
        logger.info(caller+" - "+message);                          // → Log4j2
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.PASS, message); // → ExtentReports
        }
    }

    public static void fail(String message) {
         String caller = getCaller();
        logger.error(caller+" - "+message);                          // → Log4j2
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.FAIL, message); // → ExtentReports
        }

    }
    
    public static void skip(String message) {
         String caller = getCaller();
        logger.info(caller+" - "+message);                          // → Log4j2
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.SKIP, message); // → ExtentReports
        }
    }

    public static void warn(String message) {
         String caller = getCaller();
        logger.info(caller+" - "+message);                          // → Log4j2
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.WARNING, message); // → ExtentReports
        }
    }

    private static String getCaller() {
    StackTraceElement[] stack = Thread.currentThread().getStackTrace();
    // stack[0] = getStackTrace, [1] = getCaller, [2] = info/pass/..., [3] = actual caller
    return stack[3].getClassName() + "." + stack[3].getMethodName();
}
}
