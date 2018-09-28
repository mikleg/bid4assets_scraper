package grabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    private String crashMessage = "Crash in:";
    private String exePath = "C:\\Users\\mikle\\lc101\\auc_java\\chromedriver.exe";;
    private String ffDriverPath = "C:\\Users\\mikle\\lc101\\auc_java\\geckodriver.exe";
    private String mainUrl = "https://secure.bid4assets.com/mvc/storefront/KingCountyRES?col=2913";
    private String pathToPlus = "//*[@id=\"auction-folders-wp\"]/h4/a";
    //a[@href="'+url+'"]
    ////*[@id="auction-folders-wp"]/h4/a
    //*[@id="auction-folders-wp"]
    //*[@id="accordion"]/div[3]
    //*[@id="accordion"]/div[6]
    ////*[@id="auction-folders-wp"]/h4/a
    //*[@id="auction-folders-wp"]/h4/a
    //*[@id="auction-folders-wp"]/h4/a
    private String pathToAucs = "//*[@id=\"folderListView\"]/div";
    private String pathToLots = "//*/table/tbody/tr[*]";
    private String pathToLot = "/td[3]/a";
    private String pathToLastElement = "//*[@id=\"additional-info-footer\"]/div[4]/ul/li[4]/a"; //for checking that page is loaded
    private String pathToAddnlData2 = "//*[@id=\"kingcounty_gov_cphContent_LinkButtonDetail\"]";
    private String partPath1 = "//*[@id=\"auctionGrid-";
    private String partPath2 = "/table/tbody/tr[";
    private Integer[] aucs ={2913,2914,2915,2916,2917};


    //Path to data
    private Map<String,String> PermanentDataMap;
    private Map<String,String> VariableDataMap;
    private Map<String,String> LinksDataMap;
    private Map<String,String> AdditionalDataMap;
    private Map<String,String> AdditionalDataMap2;
    private Map<String,String> timeStamp;
    private Map<String,String> shortMap;
    private Map<String,String> var2Map;


    public Map<String,String> getPermanentMap(){
        if(PermanentDataMap == null || PermanentDataMap.isEmpty()){
            PermanentDataMap = new HashMap<String, String>();
            PermanentDataMap.put("b4aNumber", "//*[@id=\"auction-content\"]/h1");
            PermanentDataMap.put("minimumBid", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[2]/td");
            PermanentDataMap.put("auctionStarts", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[7]/td");
            PermanentDataMap.put("auctionCloses", "//*[@id=\"actual-close-time-block\"]");
            PermanentDataMap.put("Deposit", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[11]/td");
            //PermanentDataMap.put("url", " ");
            PermanentDataMap.put("location", "//*[@id=\"auction-detail-left\"]/div[2]/table/tbody/tr[3]/td[2]");
           // PermanentDataMap.put("aPN", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[2]/td[2]");
            PermanentDataMap.put("aPN", "//*[@id=\"auction-content\"]/div[5]/div[4]/table/tbody/tr[4]/td[2]/a");
            //*[@id="auction-content"]/div[5]/div[4]/table/tbody/tr[3]/td[2]
            PermanentDataMap.put("legalDescription", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[3]/td[2]");
          //  PermanentDataMap.put("gIS", "");
          //  PermanentDataMap.put("assessorInfo", "");
          //  PermanentDataMap.put("titleReport", "");
            PermanentDataMap.put("assessedValuesDate", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[2]/tbody/tr[2]/td[2]");
            PermanentDataMap.put("improvementsValue", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[2]/tbody/tr[3]/td[2]");
            PermanentDataMap.put("LandValue", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[2]/tbody/tr[5]/td[2]");
            PermanentDataMap.put("aPN", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[2]/td[2]");
        }
        return  PermanentDataMap;

    }

    public Map<String,String> getShortMap(){
        if(shortMap == null || shortMap.isEmpty()){
            shortMap = new HashMap<String, String>();
        //    shortMap.put("aPN", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[2]/td[2]");
                shortMap.put("aPN", "//*[@id=\"auction-content\"]/div[5]/div[4]/table/tbody/tr[4]/td[2]/a");
            //*[@id="auction-content"]/div[5]/div[4]/table/tbody/tr[4]/td[2]/a
        }
        return  shortMap;

    }

    public Map<String,String> getVar2Map(){
        if(var2Map == null || var2Map.isEmpty()){
            var2Map = new HashMap<String, String>();
            var2Map.put("WinningBid", "Winning Bid:");
            var2Map.put("Winner", "Winner:");
            var2Map.put("AuctionStarted", "Auction Started:");
            var2Map.put("AuctionClosed", "Auction Closed:");

        }
        return  var2Map;

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
            VariableDataMap.put("auctionType", "//*[@id=\"auction-right-panel\"]/div[2]/table/tbody/tr[2]/td");
            VariableDataMap.put("WinningBid", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[2]/td");
            VariableDataMap.put("Winner", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[3]/td");
            VariableDataMap.put("AuctionStarted", "//*[@id=\"auction-right-panel\"]/div[3]/table/tbody/tr[8]/td");
            VariableDataMap.put("AuctionClosed", "//*[@id=\"actual-close-time-block\"]/span");
            //VariableDataMap.put("timestamp", "");
        }
        return  VariableDataMap;

    }

    public Map<String,String> getLinksMap(){
        if(LinksDataMap == null || LinksDataMap.isEmpty()){
            LinksDataMap = new HashMap<String, String>();
            LinksDataMap.put("gIS", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[5]/td[2]/a");
            //*[@id="auction-content"]/div[5]/div[4]/table/tbody/tr[4]/td[2]/a
           // LinksDataMap.put("assessorInfo", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[6]/td[2]/li[1]/a");
            LinksDataMap.put("assessorInfo", "//*[@id=\"auction-content\"]/div[5]/div[4]/table/tbody/tr[4]/td[2]/a");
            //LinksDataMap.put("titleReport", "//*[@id=\"auction-content\"]/div[5]/div[4]/table[1]/tbody/tr[6]/td[2]/li[2]/a");
        }
        return  LinksDataMap;

    }

    public Map<String,String> getTimestampMap(){
        if(timeStamp == null || timeStamp.isEmpty()){
            timeStamp = new HashMap<String, String>();
            timeStamp.put("timeStamp", " ");
            timeStamp.put("aPN", " ");
        }
        return  timeStamp;
    }


    public Map<String,String> getAdditionalDataMap(){
        if(AdditionalDataMap == null || AdditionalDataMap.isEmpty()){
            AdditionalDataMap = new HashMap<String, String>();
            AdditionalDataMap.put("lotSize", "Land SqFt");
            //AdditionalDataMap.put("views", "Views");
            AdditionalDataMap.put("unbuildable", "Unbuildable");
            //AdditionalDataMap.put("numberOfBedrooms", "Number Of Bedrooms");
           // AdditionalDataMap.put("numberOfBaths", "Number Of Baths");
            //AdditionalDataMap.put("grade", "Grade");
            AdditionalDataMap.put("jurisdiction", "Jurisdiction");
            //AdditionalDataMap.put("waterfront", "Waterfront");
            //AdditionalDataMap.put("waterfront", "Waterfront");
            //AdditionalDataMap.put("Parcel", "Parcel");
            //AdditionalDataMap.put("constructionClass", "Construction Class");
            AdditionalDataMap.put("presentUse", "Present Use");
            AdditionalDataMap.put("zoning", "Zoning");
            AdditionalDataMap.put("water", "Water");
           // AdditionalDataMap.put("sewerSeptic", "Sewer/Septic");
            AdditionalDataMap.put("RestrictiveSizeShape", "Restrictive Size Shape");
           // AdditionalDataMap.put("Zoning", "Zoning");
            //AdditionalDataMap.put("Water", "Water");
            AdditionalDataMap.put("SewerSeptic", "Sewer/Septic");
            AdditionalDataMap.put("RoadAccess", "Road Access");
            AdditionalDataMap.put("Parking", "Parking");
            AdditionalDataMap.put("StreetSurface", "Street Surface");

        }
        return  AdditionalDataMap;

    }

    public Map<String,String> getAdditionalDataMap2(){
        if(AdditionalDataMap2 == null || AdditionalDataMap2.isEmpty()){
            AdditionalDataMap2 = new HashMap<String, String>();
            AdditionalDataMap2.put("Jurisdiction", "Jurisdiction");
            AdditionalDataMap2.put("SiteAddress", "Site Address");
            AdditionalDataMap2.put("PercentageUnusable", "Percentage Unusable");
            AdditionalDataMap2.put("RestrictiveSizeShape", "Restrictive Size Shape");
            AdditionalDataMap2.put("Zoning", "Zoning");
            AdditionalDataMap2.put("Water", "Water");
            AdditionalDataMap2.put("SewerSeptic", "Sewer/Septic");
            AdditionalDataMap2.put("RoadAccess", "Road Access");
            AdditionalDataMap2.put("Parking", "Parking");
            AdditionalDataMap2.put("StreetSurface", "Street Surface");
            AdditionalDataMap2.put("Unbuildable", "Unbuildable");





        }
        return  AdditionalDataMap2;

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
//it takes a number of row (0..9) and returns path to lot
    public String getPathToLot(Integer number, Integer aucNumber) {

        return partPath1 + aucs[aucNumber].toString() + "\"]" + partPath2 + number.toString() + "]" + pathToLot;
    }

    public String getCrashMessage() {
        return crashMessage;
    }

    public String getPathToLastElement() {
        return pathToLastElement;
    }

    public String getPathToAddnlData2() {
        return pathToAddnlData2;
    }
}
