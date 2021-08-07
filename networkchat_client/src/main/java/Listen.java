import java.io.BufferedReader;
import java.io.IOException;

public class Listen extends Thread {
    private BufferedReader in;

    public Listen(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                String message = in.readLine();
                if (message != null) {
                    System.out.println(message);
                    Client.getLogger().info(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}