import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class SocketIOClient {
    DBConnection dbConnection = new DBConnection();

    Socket sock = IO.socket("http://smart.sum.ba/parking-events");
    public SocketIOClient() throws URISyntaxException {
        sock.on("parking-lot-state-change", new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                JSONObject obj = (JSONObject)objects[0];
                System.out.println(obj);
            }

        });
    }

    public void connect(){
        sock.connect();
    }
}