import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.event.*;
public class Main {
    public static final String TITLE = "Chatter";
    public static final String VERSION = "1.0";
    public static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        ImageIcon icon = Helper.getResourceAsImageIcon("icon-128.png");
        
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            Logger.error(e);
        }
        
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
        
        
        frame.setTitle(TITLE);
        frame.setIconImage(icon.getImage());
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
                
        Connector connector = new Connector();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                connector.disconnect();
                System.exit(0);
            }
        });
    }
}
