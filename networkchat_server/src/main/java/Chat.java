import java.io.*;
import java.net.Socket;

public class Chat implements Runnable {
    private Socket client;
    private String name;
    private PrintWriter out;

    public Chat(Socket client) throws IOException {
        this.client = client;
        this.out = new PrintWriter(client.getOutputStream(), true);
    }

    public PrintWriter getOut() {
        return out;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

            choosName(in);

            while (client.isConnected()) {
                String message = in.readLine();
                if (message != null) {
                    System.out.println(name + ": " + message);
                    Server.getLogger().info(name + ": " + message);
                    for (Chat socket : Server.getList()) {
                        socket.getOut().println(getName() + ": " + message);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void choosName(BufferedReader in) throws IOException {
        out.println("Enter your name");
        name = in.readLine();

        System.out.println(name + " присоединился к чату");
        for (Chat client : Server.getList()) {
            client.getOut().println(name + " присоединился к чату");
            Server.getLogger().info(name + " присоединился к чату");
        }
    }
}
