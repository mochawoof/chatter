import javax.swing.JOptionPane;
public class ErrorHandler {
    public static void log(Exception e) {
        e.printStackTrace();
    }
    public static void show(Exception e) {
        log(e);
        JOptionPane.showMessageDialog(Main.frame, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
