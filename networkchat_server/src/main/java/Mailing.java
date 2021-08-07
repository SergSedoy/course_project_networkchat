import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mailing extends Thread {
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String messageServer = reader.readLine();
                for (Chat socket : Server.getList()) {
                    socket.getOut().println("SERVER: " + messageServer);
                    Server.getLogger().info("SERVER: " + messageServer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
