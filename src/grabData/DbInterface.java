package grabData;

import java.util.HashMap;
import java.util.Map;

public interface DbInterface {

    boolean isTableExist(String table);
    boolean isPermanentDataExist(Map<String, String> map);
    void createNewTable(String name, Map<String, String> map);
    //void addPermanentData(Map<String, String> map);
    void addData(String tableName, Map<String, String> map);
    void changeVariableData(Map<String, String> map);


}
