// Demonstrating Client-side Programming
import java.io.*;
import java.net.*;

public class Client {
  
    // Initialize socket and input/output streams
    private Socket s = null;
    // private DataInputStream in = null;
    private BufferedReader in = null;
    private DataOutputStream out = null;

    // Constructor to put IP address and port
    public Client(String addr, int port)
    {
        // Establish a connection
        try {
            s = new Socket(addr, port);
            System.out.println("Connected");

            // Takes input from terminal
            // in = new DataInputStream(System.in);
            in = new BufferedReader(new InputStreamReader(System.in));

            

            // Sends output to the socket
            out = new DataOutputStream(s.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

        // String to read message from input
        String m = "";
        des desfun = new des();
        // Keep reading until "Over" is input
        while (!m.equals("Over")) {
            try {
                m = in.readLine();
                
                
                String key = "AABB09182736CCDD";

                String encrypted = desfun.encrypt(m, key);
                String cipher_text = desfun.bin2hex(encrypted);
                System.out.println("Sending ciphertext of "+ m + " = "+cipher_text);
                out.writeUTF(cipher_text);
            }
            catch (IOException i) {
                System.out.println(i);
            }
        }

        // Close the connection
        try {
            in.close();
            out.close();
            s.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Client c = new Client("127.0.0.1", 5000);
    }
}