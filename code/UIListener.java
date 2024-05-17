/**
 * Handles custom listeners in a new thread.
 */
public class UIListener extends Thread {
    private long started;
    private long counter;
    public void run() {
        started = System.currentTimeMillis();
        counter = 0;
        while (true) {
            long time = System.currentTimeMillis() - started;
            counter++;
            
            //Status ... connecting animation
            if (true) {
                Main.status.setText("Connecting.");
                for (int i=0; i < counter % 3; i++) {
                    Main.status.setText(Main.status.getText() + ".");
                }
            }
            
            //Update every 300ms
            try {
                sleep(300);
            } catch (InterruptedException e) {
                Logger.error(e);
            }
        }
    }
}
