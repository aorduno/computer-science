package CTCI;

public class LogUtils {
    public static void logMessage(String message) {
        logMessage(message, true);
    }

    public static void logMessage(String message, boolean printNewLine) {
        if (printNewLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }
}
