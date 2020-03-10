import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URISyntaxException;

public class SocketIOClient {
    DBConnection dbConnection = new DBConnection();
    FireBase fireBase = new FireBase();

    Socket sock = IO.socket("http://smart.sum.ba/parking-events");

    public SocketIOClient() throws URISyntaxException, IOException {
        sock.on("parking-lot-state-change", new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                JSONObject obj = (JSONObject) objects[0];
                try {
                    fireBase.connectFB();
                    fireBase.updateFB(obj);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

                dbConnection.connect();
                dbConnection.saveToDatabase(obj);
            }

        });
    }

    public void connect() {
        sock.connect();
    }
}