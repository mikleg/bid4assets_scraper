package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class KingCounty {
    Base base;
    String driverBrowser = "chrome";
    WebDriver driver;
    Settings settings;
    Db mydb;
    WebElement lot;
    List<WebElement> lots;
    List<String> allLots = new ArrayList<String>() ;

    public void runScraper(){
        base = new Base();
        mydb = new Db();
        settings = new Settings();
        mydb.createNewDatabase("test.db");
        WebElement plus;
        driver = base.getNewDriver(driverBrowser);
        driver.get(settings.getMainUrl());
        List<WebElement> articles = new ArrayList();
        List<WebElement> refs = new ArrayList<>();
        base.sleep(1000);
         //   if (base.isElementOnPage(By.id("auction-folders-wp"))) {
        //---- if (auctions folder) collapsed, click it
        WebElement cook = driver.findElement(By.cssSelector("a[aria-label='dismiss cookie message']"));
        if (cook.isDisplayed()) {
            cook.click();
        }
        WebElement plus2 = driver.findElement(By.cssSelector("a[href='#collapseFive']")); //second level menu // System.out.println("debug "  + plus2.getAttribute("aria-expanded")); //debug
            if (plus2.getAttribute("aria-expanded").equals("false"))  // click if not expanded
                {
                    base.elementClick(plus2, "auc click:");//           System.out.println("debug "  + aucExpanded); //debug
                    base.sleep(1800);
                }
         //--- uncollapse all auction folders
        for (int k=0; k < 5; k++) {
            List<WebElement> labels = base.getListByCSS(".bsnone.pl50", "no info"); //System.out.println("debug labels" + labels.get(k)); //debug
            base.sleep(1800);
            articles = base.getListByClass("ac-large", "no info"); //System.out.println("debug articles" + articles.get(k)); //debug
            base.sleep(1800);
            if (!articles.get(k).getAttribute("style").equals("height: 740px;")) {// click if not expanded
                Actions actions = new Actions(driver);
                actions.moveToElement(labels.get(k),0,-1).perform();
                base.sleep(1800);
                base.elementClick(labels.get(k), "auc click:"); //if auction folder is closed -- open it
                base.sleep(1800);
            }
            List<WebElement> badRefs = new ArrayList<>();
            WebElement el = articles.get(k);
            badRefs = el.findElements(By.tagName("a"));

            for (WebElement ref : badRefs) {
                if (!ref.getAttribute("class").equals("k-link"))
                    refs.add(ref);
            }
         }
         //------delete all ref with class k-link

        int a = 1; //debug
        //  List<WebElement> aucs = base.getListByXpath(settings.getPathToAucs(), " get aucs");
          //  for(int s =0; s < aucs.size(); s++){

          //  }


            //lots = base.getElements(aucs.get(4), settings.getPathToLots(), " get lots");
           //  WebElement lotik = driver.findElement(By.xpath("//*[@for=\"ac-2914\"]"));
             //WebElement lotik = base.getListByXpath(   base."//*[@for=\"ac-2913\"]", "bkaka");
           //  lots =  base.getElements(lotik,  settings.getPathToLots(), " get lots");
         /*   WebElement debug1 =  base.getElement(lots.get(0),"//*[" + 1 + "]" + settings.getPathToLot(), "get lot");
            WebElement debug2 =  base.getElement(lots.get(18),"//*[" + 19 + "]" + settings.getPathToLot(), "get lot");
            String t1 = debug1.getAttribute("href");
            String t2 = debug2.getAttribute("href");
            ////*[@id="auctionGrid-2915"]
             //row //*[@id="auctionGrid-2915"]/table/tbody/tr[1]
             //*[@id="auctionGrid-2915"]/table/tbody/tr[1]




*/
             int debug4=1;
             for (int l=0; l < 5; l++){
                 for (int j=1; j < 11; j ++){
                     if (base.isElementOnPage(By.xpath(settings.getPathToLot(j, l)))){
                         lot = driver.findElement(By.xpath(settings.getPathToLot(j, l)));
                         allLots.add(lot.getAttribute("href"));
                     }
                 }
             }

/*             for (int j=0; j < 10; j ++)
               // gatherAuc(j,  lots);
            {
                if(base.isElementOnPage(lots.get(j))){
                  //  Integer k = j % 10;
                  //  lot = base.getElement(lots.get(j),"//*[" + (k+1) + "]" + settings.getPathToLot(), "get lot");
                //    Integer number = (k + 1);
                  //  String pathTol = "//*[@id=\"auctionGrid-2915\"]"
                  //          + "/table/tbody/tr[" + number.toString() + "]" + settings.getPathToLot();
                    System.out.println("pathTol=" + settings.getPathToLot());
                    lot = driver.findElement(By.xpath(pathTol));
                    allLots.add(lot.getAttribute("href"));
                }

            }*/
        //*[@id="folderListView"]/div[1]/label
//*[@id="auctionGrid-2913"]/table/tbody/tr[1]/td[3]/a
        //*[@id="auctionGrid-2914"]/table/tbody/tr[1]/td[3]/a
        //*[@id="folderListView"]/div[2]/label
            driver.close();

            //TODO find every row by xpath and extract links



/*        for (WebElement lotik:lots
                ) {
            otsL.add( lotik.getAttribute("href"));
        }*/
/*        lots = base.getElements(aucs.get(0), settings.getPathToLots(), " get lots");
        for (int j=1; j < lots.size(); j ++)
            gatherAuc(j,  lots);*/

/*        for (int k=0; k < aucs.size(); k++){
            base.elementClick(aucs.get(k), "auc click:" );
            lots = base.getElements(aucs.get(k), settings.getPathToLots(), " get lots");
            System.out.println("debug:" + lots.size());
                for (int j=1; j < lots.size(); j ++)
            gatherAuc(j,  lots);
        }*/


   /*     base.elementClick(aucs.get(3), "auc click:" );
        lots = base.getElements(aucs.get(3), settings.getPathToLots(), " get lots");
        System.out.println("debug:" + lots.size());
        gatherAuc(4,  lots);*/
        //driver.close();
        int debug = 1;
        //gatherDataPage(allLots.get(3), true);
        for (String link:
             allLots) {
            gatherDataPage(link, true);

        }
    }

/*
    private void gatherAuc(int num, List<WebElement> lots){
        if(base.isElementOnPage(lots.get(num))){

            lot = base.getElement(lots.get(num),"//*[" + num + "]" + settings.getPathToLot(), "get lot");
            base.elementClick(lot, "click lot");
            gatherDataPage();

            //driver.navigate().back();

        }
    }
*/


        //private getAucInfo()
    private void gatherDataPage(String aucUrl, boolean useAddl){
        //TO DO replace on wait
        driver = base.getNewDriver(driverBrowser);
        driver.get(aucUrl);
        base.sleep(800);
        if(!base.isElementOnPage(By.xpath(settings.getPathToLastElement()))){
            base.sleep(3500);
           }
        Map<String, String> varMap = base.getTexts(settings.getVariableMap());
       //
      //  Map<String, String> varMap2 = base.getTextsBySiblingsText(settings.getVar2Map(), ".//td");
     //   varMap.putAll(varMap2);
        Map<String, String> shortMap = base.getTexts(settings.getShortMap());
     //   if (varMap.get("auctionType").equals("None")){
            Map<String, String> permMap = base.getTexts(settings.getPermanentMap());
            Map<String, String> linksMap = base.getLinks(settings.getLinksMap());
            String url = driver.getCurrentUrl();
            permMap.put("url", url);
            permMap.put("aPN", shortMap.get("aPN"));
            System.out.println(url);
            Map allPerm =  new HashMap<>(permMap);
            if(useAddl){
                driver.navigate().to(linksMap.get("assessorInfo"));
                base.sleep(2500);
                Map<String,String> aaa = settings.getAdditionalDataMap();
                Map<String, String> addlDataMap = base.getTextsBySiblingsText(aaa, ".//td[2]");
/*                if(base.isElementOnPage(By.xpath(settings.getPathToAddnlData2()))){
                    WebElement elt = driver.findElement(By.xpath(settings.getPathToAddnlData2()));
                    elt.click();
                }*/

                //Map<String, String> addlDataMap2 = base.getTextsBySiblingsText(settings.getAdditionalDataMap2(), ".//td[2]");
                allPerm.putAll(addlDataMap);
               // allPerm.putAll(addlDataMap2);
                allPerm.putAll(linksMap);
                mydb.createNewTable("permanentData", allPerm);
                if(!mydb.isPermanentDataExist(allPerm)){
                    mydb.addData("permanentData", allPerm);
                }
            }

       // }



        varMap.put("timeStamp", "");
        varMap.put("aPN", shortMap.get("aPN"));
        Map<String, String> timeStampMap = new HashMap<>();
        timeStampMap.put("aPN", shortMap.get("aPN"));
        timeStampMap.put("timeStamp", " ");

        //String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

        //System.out.println(timeStamp);


        //driver.close();
         //driver.navigate().back();
        //driver.navigate().back();
        //driver.navigate().back();
        //driver.navigate().back();
        driver.close();
        //mydb.createNewDatabase("test.db");



        varMap.put("timeStamp", "na");

        mydb.createNewTable("variableData", varMap);
        mydb.createNewTable("timeStamp", timeStampMap);

        varMap.remove("timeStamp");
        mydb.checkVariableData(varMap);
//TO DO check why dosen't work timestamp in variable data
// TODO replace characters non sql
        //TODO replace sleep
        //http://www.sqlitetutorial.net/sqlite-java/



    }

}
