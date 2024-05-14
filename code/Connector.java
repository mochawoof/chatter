import java.util.ArrayList;
import java.net.*;
public class Connector {
    public static final int DISCONNECTED = 0;
    public static final int CONNECTING = 1;
    public static final int CONNECTED = 2;
    
    public static final int MAX_MESSAGE_LENGTH = 1000;
    
    public int status = DISCONNECTED;
    public int port = 420;
    public byte[] headcountBytes = "HEADCOUNT".getBytes();
    
    private DatagramSocket socket;
    
    public Connector() {
        try {
            socket = new DatagramSocket(port);
            socket.setBroadcast(true);
            socket.connect(InetAddress.getByName("255.255.255.255"), port);
            listen();
        } catch (Exception e) {
            Logger.error(e);
        }
    }
    
    public void listen() {
        new Thread(() -> {
            while (status != DISCONNECTED) {
                try {
                    byte[] bytes = new byte[MAX_MESSAGE_LENGTH];
                    DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                    socket.receive(packet);
                    
                    System.out.println(new String(bytes));
                } catch (Exception e) {
                    Logger.error(e);
                }
            }
        }).start();
    }
    
    public void disconnect() {
        socket.close();
        status = DISCONNECTED;
    }
    
    public void send(byte[] bytes) {
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        try {
            socket.send(packet);
        } catch (Exception e) {
            Logger.error(e);
            status = DISCONNECTED;
        }
    }
    
    public void headcount() {
        status = CONNECTING;
        send(headcountBytes);
    }
}
