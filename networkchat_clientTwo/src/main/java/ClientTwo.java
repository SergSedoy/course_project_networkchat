import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ClientTwo {
    private static final Logger logger = LogManager.getLogger(ClientTwo.class);

    public static Logger getLogger() {
        return logger;
    }

    public static void main(String[] args) {
        ConnectionSettings connectionSettings = new ConnectionSettings();
        connectionSettings.readFileSettings();
        int serverPort = connectionSettings.getServerPort();

        try (Socket client = new Socket("localhost", serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(in.readLine());
            String name = br.readLine();
            out.println(name);
            Thread listener = new Listen(in);
            listener.start();

            while (true) {
                String message = br.readLine();
                if (message.equals("exit")) {
                    out.println(name + " покинул чат");
                    listener.interrupt();
                    Thread.sleep(1000);
                    client.close();
                    break;
                }
                out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
