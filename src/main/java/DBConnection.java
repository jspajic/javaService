import org.json.JSONObject;

import java.sql.*;

public class DBConnection {


    public void connect() {
        {
            try {
               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartSUM", "root", "");
                System.out.println("Connected to database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
