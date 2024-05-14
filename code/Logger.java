import javax.swing.JOptionPane;
public class Logger {
    public static void log(Object o) {
        System.out.println(formatLog(o));
    }
    public static String formatLog(Object o) {
        return "[ " + java.time.LocalDateTime.now().toString() + " ] " + o.toString();
    }
    public static void error(Exception e) {
        StackTraceElement[] rawTrace = e.getStackTrace();
        String trace = e.toString();
        for (StackTraceElement s : rawTrace) {
            trace += "\t" + s.getFileName() + ": " + s.getLineNumber() + ", " + s.getMethodName() + System.lineSeparator();
        }
        System.err.println(formatLog(trace));
    }
    public static void showError(Exception e) {
        error(e);
        JOptionPane.showMessageDialog(Main.frame, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
