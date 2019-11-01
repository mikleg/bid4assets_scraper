package grabData;

import java.sql.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
       // String qres6;
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + table+ "{table_name}';";
        String qres = getQueryResultToString(sql);
        return qres.length() > 0;

        }
/*    public boolean isPermanentDataExist(Map<String, String> map){
        //TODO replace parcelNumber
        String aucnumb = map.get("parcelNumber");
        String sql = "SELECT COUNT(*) FROM permanentData WHERE parcelNumber ='" + aucnumb + "' ;";
        System.out.println(getQueryResultToInt(sql));
        int res = getQueryResultToInt(sql);
        return res > 0;
    }*/

    public boolean isPermanentDataExist(Map<String, String> map){
        //TODO replace parcelNumber
        String aucnumb = map.get("aPN");
        String sql = "SELECT COUNT(*) FROM permanentData WHERE aPN ='" + aucnumb + "' ;";
        System.out.println(getQueryResultToInt(sql));
        int res = getQueryResultToInt(sql);
        return res > 0;
    }


    public  void createNewTable(String name, Map<String, String> map) {

        String sql = "CREATE TABLE IF NOT EXISTS " + name + " (\n"
                + "	id integer PRIMARY KEY,\n"
                //  + fields.substring(0, fields.length()-2)
                + getFieldsText(map)
                //+ "	id integer PRIMARY KEY,\n"
                + ");";
        runSql(sql);
    }

    public void addData(String tableName, Map<String, String> map){

        String sql = "INSERT INTO " + tableName + " (\n"
                + getFields(map) +")"
                + " VALUES\n" +
                " (\n" +
                getValues(map) +");";
        runSql(sql);
    }

    public void addVariableData(String tableName, Map<String, String> map){
        //TO DO add into timeStamp table only
        //TODO replace string in tamestamp on normal time format
        String aPN = map.get("aPN");
        String sql = "INSERT INTO " + tableName + " (\n"
                + getFields(map) +", 'timeStamp')"
                + " VALUES\n" +
                " (\n" +
                getValues(map) + ", DATETIME('now'));";
        runSql(sql);
        sql = "INSERT INTO " + "timeStamp" + " (\n"
               + " 'timeStamp' , 'aPN')"
                + " VALUES\n" +
                " (\n" +
                 "DATETIME('now'), '" + aPN +  "');";
        runSql(sql);

    }


  //it checks variable data and if it si not changed -- adds new timeStamp into table timestamps
    public boolean checkVariableData(Map<String, String> map){
        String aucnumb = map.get("aPN");

        if(getQueryResultToInt("SELECT COUNT(*) FROM timeStamp WHERE aPN ='" + aucnumb + "' ;") == 0){
            addVariableData("variableData", map);
            return true;
        }

        String sql = "SELECT * FROM timeStamp WHERE aPN ='" + aucnumb + "' ORDER BY datetime(timeStamp) DESC LIMIT 1;";
        String  timestamp = getQueryResultToString(sql);
       // System.out.println("debug timestamp=" + timestamp);
        sql = "SELECT * FROM variableData WHERE aPN ='" + aucnumb + "' AND timeStamp =  '" + timestamp + "';";
       // System.out.println(sql);
        Map<String, String> dbMap = getQueryResultToMap(sql, "variableData");
      //  String fields ="";
     //   String values ="";
        boolean isItChanged = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {

          //  fields = fields + " " + entry.getKey() + ",\n";
           // values = values + " " + entry.getValue() + ",\n";
            //System.out.println("Debug check variable data" );
            //System.out.println("Key=" + entry.getKey() + " value" + entry.getValue());
            if ((!entry.getValue().equals(dbMap.get(entry.getKey())))
                    && (!entry.getKey().equals("pageViews"))
                    && (!entry.getKey().equals("closesIn"))
                    ){
                isItChanged = true;
                System.out.println("Debug isItChanged = true Key=" + entry.getKey() + " value" + entry.getValue());
            }
        }
        if(isItChanged){
            addVariableData("variableData", map);
        }
        else {
            String aPN = map.get("aPN");
            sql = "INSERT INTO " + "timeStamp" + " (\n"
                    + " 'timeStamp' , 'aPN')"
                    + " VALUES\n" +
                    " (\n" +
                    "DATETIME('now'), '" + aPN +  "');";
            runSql(sql);
        }
        return isItChanged;
    }


    public void changeVariableData(Map<String, String> map){

    }

/*
    // it returns if variableDataChanged
    public boolean checkVariableData(Map<String, String> map){
        String aucnumb = map.get("aPN");
        String sql = "SELECT COUNT(*) FROM permanentData WHERE aPN ='" + aucnumb + "' ;";
        System.out.println(getQueryResultToInt(sql));
        return false;
    }
*/


/*    public boolean isPermanentDataExist(Map<String, String> map){
        //TODO replace parcelNumber
        String aucnumb = map.get("aPN");
        String sql = "SELECT COUNT(*) FROM permanentData WHERE aPN ='" + aucnumb + "' ;";
        System.out.println(getQueryResultToInt(sql));
        int res = getQueryResultToInt(sql);
        return res > 0;
    }*/

    private String getFieldsText(Map<String, String> map){
        String fields ="";
        for (Map.Entry<String, String> entry : map.entrySet()) {

            fields = fields + " " + entry.getKey() + " text,\n";
        }
        return fields.substring(0, fields.length()-2);
    }

    private String getFields(Map<String, String> map){
        String fields ="";
        for (Map.Entry<String, String> entry : map.entrySet()) {

            fields = fields + " " + entry.getKey() + ",\n";
        }
        return fields.substring(0, fields.length()-2);
    }

    private String getValues(Map<String, String> map){
        String fields ="";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().length()>0)
           // {fields = fields + " " + "'"+entry.getValue().replaceAll("\\s","")+"'" + ",\n";}
            {

                fields = fields + " " + "'"+entry.getValue().replaceAll("'"," ")+"'" + ",\n";
            }
            else {
                {fields = fields + " " + "'NA'" + ",\n";}
            }
        }
        return fields.substring(0, fields.length()-2);
    }

    private boolean runSql(String sql){
       // System.out.println("sql_debug=" + sql);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
            //conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int getQueryResultToInt(String sql){
       // String sql = "SELECT id, name, capacity FROM warehouses";
        int total = -1;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                total= rs.getInt(1);
            }
           // conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    public String  getQueryResultToString(String sql){
        String res= "";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
/*                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();*/
            // loop through the result set
            while (rs.next()) {

                res= rs.getString("timeStamp");
               // conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public Map<String, String>  getQueryResultToMap(String sql, String tablename){
        //TODO find bug
        //String res= "";
        HashMap<String,String> resMap = new HashMap<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) {

                ResultSetMetaData metadata = rs.getMetaData();

                int columnCount = metadata.getColumnCount();
                // loop through the result set
                for(int i  = columnCount; i > 0; i--){
                    resMap.put(metadata.getColumnName(i), rs.getString(i) );

                }
            }
           //conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resMap;
    }

    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
                Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


}

