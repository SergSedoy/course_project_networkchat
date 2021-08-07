import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    @Test
    public void streamTest() throws IOException {
        Socket client = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("hello".getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        when(client.getOutputStream()).thenReturn(out);
        when(client.getInputStream()).thenReturn(in);
        new Listen(br);
        assertEquals(out.toString(), "");
        assertTrue(out.toString().equals(""));
    }
}
