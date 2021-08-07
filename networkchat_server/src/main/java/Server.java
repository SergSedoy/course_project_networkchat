import java.io.*;
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
    private static final int SERVER_PORT = 34345;
    private static List<Chat> list = new ArrayList<>();

    static {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\user\\IdeaProjects\\course project\\networkchat_server\\src\\main\\resources\\settings.txt");
            writer.write(SERVER_PORT + "");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static Logger getLogger() {
        return logger;
    }

    public static List<Chat> getList() {
        return list;
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
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
