import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    private static List<Chat> list = new ArrayList<>();
    private static ConnectionSettings connectionSettings = new ConnectionSettings();
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static Logger getLogger() {
        return logger;
    }

    public static List<Chat> getList() {
        return list;
    }

    public static void main(String[] args) {
        connectionSettings.createFileSettings();

        try (ServerSocket server = new ServerSocket(connectionSettings.getServerPort())) {
            Mailing mailingServer = new Mailing();
            mailingServer.start();

            while (!server.isClosed()) {
                Socket client = server.accept();
                Chat newClient = new Chat(client);
                executorService.execute(newClient);
                adding(newClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adding(Chat newClient) {
        list.add(newClient);
    }

}
