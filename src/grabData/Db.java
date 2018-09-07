package grabData;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Db implements DbInterface {
    private static String url;
    public static void createNewDatabase(String fileName) {

        url = "jdbc:sqlite:./db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isTableExist(String table){
        return false;
    }
    public boolean isPermanentDataExist(Map<String, String> map){
        return false;
    }
    public  void createNewTable(String name, Map<String, String> map) {

        // SQL statement for creating a new table
        String fields ="";
        for (Map.Entry<String, String> entry : map.entrySet()) {

            fields = fields + " " + entry.getKey() + " text,\n";
        }

        String sql = "CREATE TABLE IF NOT EXISTS " +  name + " (\n"
                 + "	id integer PRIMARY KEY,\n"
                + fields.substring(0, fields.length()-2)
                //+ "	id integer PRIMARY KEY,\n"
              + ");";

        System.out.println("sql_debug=" + sql);
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPermanentData(Map<String, String> map){

    }
    public void changeVariableData(Map<String, String> map){

    }

}

