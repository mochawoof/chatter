import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class Main {
    public static final String TITLE = "Chatterbox";
    public static final String VERSION = "1.0";
    private static String[] args;
    public static JFrame frame;
    public static JLabel status;
    private static UIListener listener;
    public static void main(String[] args) {
        //Initialize
        Main.args = args;
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        ImageIcon icon = Resources.getImageIcon("icon-128.png");
        
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            Logger.error(e);
        }
        
        //Add components
        status = new JLabel(" ", SwingConstants.RIGHT);
        status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(status, BorderLayout.PAGE_END);
        
        JMenuBar bar = new JMenuBar();
        frame.add(bar, BorderLayout.PAGE_START);
            JMenu connectionMenu = new JMenu("Connection");
            bar.add(connectionMenu);
            
            JMenu helpMenu = new JMenu("Help");
            bar.add(helpMenu);
                JMenuItem aboutItem = new JMenuItem("About");
                helpMenu.add(aboutItem);
                aboutItem.addActionListener(e -> {
                    JOptionPane.showMessageDialog(frame,
                        TITLE + " version " + VERSION,
                        "About",
                        JOptionPane.INFORMATION_MESSAGE,
                        icon
                    );
                });
        
        
        //Finalize frame, register window listeners
        listener = new UIListener();
        listener.start();
        frame.setTitle(TITLE);
        frame.setIconImage(icon.getImage());
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
                
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                listener.stop();
                frame.dispose();
                System.exit(0);
            }
        });
    }
    private static void relaunch() {
        frame.dispose();
        main(args);
    }
}
