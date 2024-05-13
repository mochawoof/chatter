import javax.swing.*;
public class Main {
    public static final String TITLE = "Chatter";
    public static final String VERSION = "1.0";
    public static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame();
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            ErrorHandler.log(e);
        }
        
        frame.setTitle(TITLE);
        frame.setIconImage(Helper.getResourceAsImage("icon-128.png"));
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
