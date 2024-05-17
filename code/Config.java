import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Manages a Properties file.
 */
public class Config {
    private Properties file;
    private String path;
    private String comment;
    private boolean saveOnSet;
    public Config(String path, String comment, boolean saveOnSet) {
        this.path = path;
        this.comment = comment;
        this.saveOnSet = saveOnSet;
        file = new Properties();
        try {
            FileInputStream stream = new FileInputStream(path);
            file.load(stream);
            stream.close();
        } catch (IOException e) {
            Logger.error(e);
        }
    }
    public Config(String path, String comment) {
        this(path, comment, false);
    }
    public Config(String path) {
        this(path, "", false);
    }
    public String get(String key) {
        return (String) file.get(key);
    }
    /**
     * Templates are formatted like so:
     * HELLO:world, HI:world
     */
    public String getTemplated(String key, String t) {
        String got = get(key);
        String[] templates = t.split(",");
        for (String s : templates) {
            s = s.trim();
            String[] pair = s.split(":");
            if (pair.length > 1) {
                got = got.replaceAll(pair[0], pair[1]);
            }
        }
        return got;
    }
    public String getWithDefault(String key, String d) {
        String got = get(key);
        if (got == null) {
            set(key, d);
            return d;
        }
        return got;
    }
    public void set(String key, String value) {
        file.put(key, value);
        if (saveOnSet) {
            save();
        }
    }
    public void save() {
        try {
            FileOutputStream stream = new FileOutputStream(path);
            file.store(stream, comment);
            stream.close();
        } catch (IOException e) {
            Logger.error(e);
        }
    }
}
