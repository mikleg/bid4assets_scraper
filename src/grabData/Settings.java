package grabData;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private String crashMessage = "Crash in:";
    private String exePath = "C:\\Users\\mikle\\lc101\\auc_java\\chromedriver.exe";;
    private String ffDriverPath = "C:\\Users\\mikle\\lc101\\auc_java\\geckodriver.exe";
    private String mainUrl = "https://secure.bid4assets.com/mvc/storefront/KingWASept18";
    private String pathToPlus = "/html/body/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/h4/a";
    private String pathToAucs = "//*[@id=\"folderListView\"]/div";
    private String pathToLots = "//*/table/tbody/tr";
    private String pathToLot = "//*/td[3]/a";

    //Path to data
    private Map<String,String> PermanentDataMap;
    private Map<String,String> VariableDataMap;
    public Map<String,String> getPermanentMap(){
        if(PermanentDataMap == null || PermanentDataMap.isEmpty()){
            PermanentDataMap = new HashMap<String, String>();
            PermanentDataMap.put("b4aNumberPath", "//*[@id=\"auction-content\"]/h1");
            PermanentDataMap.put("minimumBidPath", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[2]/td");
            PermanentDataMap.put("auctionStartsPath", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[7]/td");
            PermanentDataMap.put("auctionClosesPath", "//*[@id=\"actual-close-time-block\"]");
            /*PermanentDataMap.put("DepositPath", "");
            PermanentDataMap.put("urlPath", "");
            PermanentDataMap.put("locationPath", "");
            PermanentDataMap.put("aPNPath", "");
            PermanentDataMap.put("legalDescriptionPath", "");
            PermanentDataMap.put("gISPath", "");
            PermanentDataMap.put("assessorInfoPath", "");
            PermanentDataMap.put("titleReportPath", "");
            PermanentDataMap.put("assessedValuesDatePath", "");
            PermanentDataMap.put("improvementsValuePath", "");
            PermanentDataMap.put("LandValuePath", "");*/
        }
        return  PermanentDataMap;

    }

    public Map<String,String> getVariableMap(){
        if(VariableDataMap == null || VariableDataMap.isEmpty()){
            VariableDataMap = new HashMap<String, String>();
            VariableDataMap.put("b4aNumberPath", "");
            VariableDataMap.put("currentBidPath", "");
            VariableDataMap.put("statusPath", "");
            VariableDataMap.put("numberOfBidsPath", "");
            VariableDataMap.put("closesInPath", "");
            VariableDataMap.put("pageViewsPath", "");
            VariableDataMap.put("timestampPath", "");
        }
        return  VariableDataMap;

    }

/*

    private String b4aNumberPath = "";
    private String minimumBidPath = "";
    private String auctionStartsPath = "";
    private String auctionClosesPath = "";
    private String DepositPath = "";
    private String urlPath = "";
    private String locationPath = "";
    private String aPNPath = "";
    private String legalDescriptionPath = "";
    private String gISPath = "";
    private String assessorInfoPath = "";
    private String titleReportPath = "";
    private String assessedValuesDatePath = "";
    private String improvementsValuePath = "";
    private String LandValuePath = "";
    private String currentBidPath = "";
    private String statusPath = "";
    private String numberOfBidsPath = "";
    private String closesInPath = "";
    private String pageViewsPath = "";
    private String timestampPath = "";
*/








    public String getExePath() {
        return exePath;
    }

    public  String getFfDriverPath() {
        return ffDriverPath;
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public String getPathToPlus() {
        return pathToPlus;
    }

    public String getPathToAucs() {
        return pathToAucs;
    }

    public String getPathToLots() {
        return pathToLots;
    }

    public String getPathToLot() {
        return pathToLot;
    }

    public String getCrashMessage() {
        return crashMessage;
    }
}
