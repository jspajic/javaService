import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        SocketIOClient sioc = new SocketIOClient();

        sioc.connect();
    }
}
