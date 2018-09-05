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
    private String pathToLastElement = "//*[@id=\"additional-info-footer\"]/div[4]/ul/li[4]/a"; //for checking that page is loaded

    //Path to data
    private Map<String,String> PermanentDataMap;
    private Map<String,String> VariableDataMap;
    private Map<String,String> LinksDataMap;
    public Map<String,String> getPermanentMap(){
        if(PermanentDataMap == null || PermanentDataMap.isEmpty()){
            PermanentDataMap = new HashMap<String, String>();
            PermanentDataMap.put("b4aNumber", "//*[@id=\"auction-content\"]/h1");
            PermanentDataMap.put("minimumBid", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[2]/td");
            PermanentDataMap.put("auctionStarts", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[7]/td");
            PermanentDataMap.put("auctionCloses", "//*[@id=\"actual-close-time-block\"]");
            PermanentDataMap.put("Deposit", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[11]/td");
           // PermanentDataMap.put("url", "");
            PermanentDataMap.put("location", "//*[@id=\"auction-detail-left\"]/div[2]/table/tbody/tr[3]/td[2]");
            PermanentDataMap.put("aPN", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[2]/td[2]");
            PermanentDataMap.put("legalDescription", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[3]/td[2]");
          //  PermanentDataMap.put("gIS", "");
          //  PermanentDataMap.put("assessorInfo", "");
          //  PermanentDataMap.put("titleReport", "");
            PermanentDataMap.put("assessedValuesDate", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[2]/tbody/tr[2]/td[2]");
            PermanentDataMap.put("improvementsValue", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[2]/tbody/tr[3]/td[2]");
            PermanentDataMap.put("LandValue", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[2]/tbody/tr[5]/td[2]");
        }
        return  PermanentDataMap;

    }

    public Map<String,String> getVariableMap(){
        if(VariableDataMap == null || VariableDataMap.isEmpty()){
            VariableDataMap = new HashMap<String, String>();
            VariableDataMap.put("b4aNumber", "//*[@id=\"auction-content\"]/h1");
            VariableDataMap.put("currentBid", "//*[@id=\"current-bid-span\"]");
            VariableDataMap.put("status", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[6]/td");
            VariableDataMap.put("numberOfBids", "//*[@id=\"num-bids-block\"]");
            VariableDataMap.put("closesIn", "//*[@id=\"time-remaining-block\"]");
            VariableDataMap.put("pageViews", "//*[@id=\"auction-detail-page-views\"]");
           // VariableDataMap.put("timestamp", "");
        }
        return  VariableDataMap;

    }

    public Map<String,String> getLinksMap(){
        if(LinksDataMap == null || LinksDataMap.isEmpty()){
              LinksDataMap = new HashMap<String, String>();
              LinksDataMap.put("gIS", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[5]/td[2]/a");
              LinksDataMap.put("assessorInfo", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[6]/td[2]/li[1]/a");
              LinksDataMap.put("titleReport", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[6]/td[2]/li[2]/a");
        }
        return  LinksDataMap;

    }


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

    public String getPathToLastElement() {
        return pathToLastElement;
    }
}
