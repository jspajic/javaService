import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public class DBConnection {

    Connection connection;


    public void connect() {
        {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartSUM", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveToDatabase(JSONObject... objects) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO parking_spaces" +
                    " (id_parking_lot,normal_occupied,handicap_occupied,handicap_available,id_parking_space," +
                    "created_at,normal_available,type,parking_space_name,id_parking_lot_type,occupied)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            JSONObject object = (JSONObject) objects[0];

            int id_parking_lot = Integer.parseInt(String.valueOf(object.getString("id_parking_lot")));
            int normal_occupied = Integer.parseInt(String.valueOf(object.getString("normal_occupied")));
            int handicap_occupied = Integer.parseInt(String.valueOf(object.getString("handicap_occupied")));
            int handicap_available = Integer.parseInt(String.valueOf(object.getString("handicap_available")));
            int id_parking_space = Integer.parseInt(String.valueOf(object.getString("id_parking_space")));
            String created_at = (String) object.get("created_at");
            int normal_available = Integer.parseInt(String.valueOf(object.getString("normal_available")));
            String type = (String) object.get("type");
            String parking_space_name = (String) object.get("parking_space_name");
            int id_parking_lot_type = Integer.parseInt(String.valueOf(object.getString("id_parking_lot_type")));
            int occupied = Integer.parseInt(String.valueOf(object.getString("occupied")));

            preparedStatement.setInt(1, id_parking_lot);
            preparedStatement.setInt(2, normal_occupied);
            preparedStatement.setInt(3, handicap_occupied);
            preparedStatement.setInt(4,handicap_available);
            preparedStatement.setInt(5,id_parking_space);
            preparedStatement.setString(6,created_at);
            preparedStatement.setInt(7,normal_available);
            preparedStatement.setString(8,type);
            preparedStatement.setString(9, parking_space_name);
            preparedStatement.setInt(10,id_parking_lot_type);
            preparedStatement.setInt(11,occupied);

            preparedStatement.executeUpdate();
            System.out.println("Database updated!");

        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }

    }
}
