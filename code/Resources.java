/**
 * Utility class for getting bundled resource files.
 */
import java.net.URL;
import javax.swing.ImageIcon;
public class Resources {
    public static String get(String path) {
        return Resources.class.getResource(path).getPath();
    }
    public static URL getURL(String path) {
        return Resources.class.getResource(path);
    }
    public static ImageIcon getImageIcon(String path) {
        return new ImageIcon(getURL(path));
    }
}
