import java.io.*;
import java.net.Socket;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Client {
    private static final Logger logger = LogManager.getLogger(Client.class);

    public static Logger getLogger() {
        return logger;
    }

    public static void main(String[] args) {

        int serverPort = identifyPort();

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

    private static int identifyPort() {
        String port = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\user\\IdeaProjects\\course project\\networkchat_server\\src\\main\\resources\\settings.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            port = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int serverPort = Integer.parseInt(port);
        return serverPort;
    }
}
