import java.io.FileWriter;

public class ConnectionSettings {
    private static final int SERVER_PORT = 34345;
    private static final String CHAT_NAME = "work";

    public int getServerPort() {
        return SERVER_PORT;
    }

    public String getChatName() {
        return CHAT_NAME;
    }

    public void createFileSettings() {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\user\\IdeaProjects\\course project\\networkchat_server\\src\\main\\resources\\settings.txt");
            writer.write(SERVER_PORT + System.getProperty("line.separator") + CHAT_NAME);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
