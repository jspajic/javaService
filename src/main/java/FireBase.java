import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FireBase {
    private DatabaseReference parking1;
    private DatabaseReference parkingSpaces;

    public void connectFB() throws IOException {

        FileInputStream serviceAccount = new FileInputStream("/home/jozo/IdeaProjects/saveToMYSQL/smartsumparking-firebase-adminsdk-1zr0m-8f10f99874.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://smartsumparking.firebaseio.com")
                .build();

        boolean hasBeenInitialized=false;

        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        for(FirebaseApp app : firebaseApps){
            if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)){
                hasBeenInitialized=true;
            }
        }

        if(!hasBeenInitialized) {
            FirebaseApp.initializeApp(options);
        }


        parking1 = FirebaseDatabase.getInstance().getReference("0");
        parkingSpaces = parking1.child("parkingSpaces");

    }

    public void updateFB(JSONObject... objects) throws JSONException {
        JSONObject obj = (JSONObject) objects[0];

        System.out.println(obj);

        parking1.child("normal_available").setValueAsync(obj.getString("normal_available"));

        DatabaseReference parking = parkingSpaces.child(String.valueOf(obj.getInt("id_parking_space") - 1));
        parking.child("occupied").setValueAsync(obj.getString("occupied"));
        parking.child("last_event").setValueAsync(obj.getString("created_at"));
        parking.child("updated_at").setValueAsync(obj.getString("created_at"));
    }


}
