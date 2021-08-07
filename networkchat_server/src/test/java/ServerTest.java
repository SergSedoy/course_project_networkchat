import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {

    @Test
    public void streamTest() throws IOException {
        Socket client = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        when(client.getOutputStream()).thenReturn(out);
        when(client.getInputStream()).thenReturn(in);
        Chat newClient = new Chat(client);
        newClient.run();
        String actual = String.format("Enter your name%s", System.getProperty("line.separator"));
        assertEquals(out.toString(), actual);
        assertTrue(out.toString().equals(actual));
    }

    @Test
    public void addingTest() throws IOException {
        Socket client = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        when(client.getOutputStream()).thenReturn(out);
        when(client.getInputStream()).thenReturn(in);
        Chat newClient = new Chat(client);
        List<Chat> expectedList = List.of(newClient);
        Server.adding(newClient);
        assertEquals(expectedList, Server.getList());
    }

}
