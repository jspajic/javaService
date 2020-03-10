import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;

public class FireBase {
    DatabaseReference ps;

    public void connectFB() throws IOException {

        FileInputStream serviceAccount = new FileInputStream("/home/jozo/IdeaProjects/saveToMYSQL/smartsumparking-firebase-adminsdk-1zr0m-8f10f99874.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://smartsumparking.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        ps = FirebaseDatabase.getInstance().getReference("parkingSpaces");

    }

    public void updateFB(JSONObject... objects) throws JSONException {
        JSONObject obj = (JSONObject) objects[0];

        DatabaseReference parking = ps.child(obj.getString("id_parking_space"));
        parking.child("occupied").setValueAsync(obj.getString("occupied"));
        parking.child("last_event").setValueAsync(obj.getString("created_at"));
        parking.child("updated_at").setValueAsync(obj.getString("created_at"));
    }


}
