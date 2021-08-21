import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConnectionSettings {
    private static int SERVER_PORT;
    private static String CHAT_NAME;

    public int getServerPort() {
        return SERVER_PORT;
    }

    public String getChatName() {
        return CHAT_NAME;
    }

    public void readFileSettings() {
        String[] arrayLine = new String[2];
        try {
            FileReader fileReader = new FileReader("\\Users\\user\\IdeaProjects\\course project\\networkchat_server\\src\\main\\resources\\settings.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < 2; i++){
                arrayLine[i] = bufferedReader.readLine();
            }
            SERVER_PORT = Integer.parseInt(arrayLine[0]);
            CHAT_NAME = arrayLine[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(CHAT_NAME);
    }
}
