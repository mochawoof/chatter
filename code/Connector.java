import java.util.ArrayList;
import java.net.*;
public class Connector {
    public static final int DISCONNECTED = 0;
    public static final int CONNECTING = 1;
    public static final int CONNECTED = 2;
    
    public static final int MAX_CONNECTIONS = 50;
    public static final byte[] HEAD_COUNT_BYTES = "HEAD_COUNT".getBytes();
    public static final String BROADCAST_ADDRESS = "255.255.255.255";
    
    public int status = DISCONNECTED;
    public int port = 420;
    
    public void requestHeadCount() {
        try {
            status = CONNECTING;
            DatagramSocket sock = new DatagramSocket();
            sock.setBroadcast(true);
            
            InetAddress broadcast = InetAddress.getByName(BROADCAST_ADDRESS);
            DatagramPacket packet = new DatagramPacket(HEAD_COUNT_BYTES, HEAD_COUNT_BYTES.length, broadcast, port);
            sock.send(packet);
            Logger.log("Requested head count...");
            
            byte[] returnBuffer = new byte[HEAD_COUNT_BYTES.length * MAX_CONNECTIONS];
            DatagramPacket returnPacket = new DatagramPacket(returnBuffer, returnBuffer.length);
            sock.receive(returnPacket);
            Logger.log("Recieved head count!");
            
            status = CONNECTED;
            sock.close();
        } catch (Exception e) {
            status = DISCONNECTED;
            Logger.showError(e);
        }
    }
}
