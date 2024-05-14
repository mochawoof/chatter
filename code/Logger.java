import javax.swing.JOptionPane;
public class Logger {
    public static void log(Object o) {
        System.out.println(formatLog(o));
    }
    public static String formatLog(Object o) {
        return "[ " + java.time.LocalDateTime.now().toString() + " ] " + o.toString();
    }
    public static void error(Exception e) {
        log(e);
        e.printStackTrace();
    }
    public static void showError(Exception e) {
        error(e);
        JOptionPane.showMessageDialog(Main.frame, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
