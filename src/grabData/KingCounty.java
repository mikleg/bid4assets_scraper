package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
      //local//
        WebElement plus;

        //WebElement lot;
      //end local//
        for (int k=0; k < 11; k++) {
            driver = base.getNewDriver(driverBrowser);
            driver.get(settings.getMainUrl());


        if (base.isElementOnPage(By.xpath(settings.getPathToPlus()))){
            plus = driver.findElement(By.xpath(settings.getPathToPlus()));
            base.elementClick(plus, "plus");
        }

        List<WebElement> aucs = base.getListByXpath(settings.getPathToAucs(), " get aucs");

        //base.sleep(1800);

            base.elementClick(aucs.get(k), "auc click:");
            base.sleep(1800);
            lots = base.getElements(aucs.get(k), settings.getPathToLots(), " get lots");
         /*   WebElement debug1 =  base.getElement(lots.get(0),"//*[" + 1 + "]" + settings.getPathToLot(), "get lot");
            WebElement debug2 =  base.getElement(lots.get(18),"//*[" + 19 + "]" + settings.getPathToLot(), "get lot");
            String t1 = debug1.getAttribute("href");
            String t2 = debug2.getAttribute("href");
*/            for (int j=0; j < lots.size(); j ++)
               // gatherAuc(j,  lots);
            {
                if(base.isElementOnPage(lots.get(j))){
                    lot = base.getElement(lots.get(j),"//*[" + (j+1) + "]" + settings.getPathToLot(), "get lot");
                    allLots.add(lot.getAttribute("href"));
                }

            }

            driver.close();

            //TODO find every row by xpath and extract links


        }
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
        gatherDataPage(allLots.get(3), true);
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
            System.out.println(url);
            Map allPerm =  new HashMap<>(permMap);
            if(useAddl){
                driver.navigate().to(linksMap.get("assessorInfo"));
                base.sleep(2500);
                Map<String, String> addlDataMap = base.getTextsBySiblingsText(settings.getAdditionalDataMap(), ".//td[2]");
                if(base.isElementOnPage(By.xpath(settings.getPathToAddnlData2()))){
                    WebElement elt = driver.findElement(By.xpath(settings.getPathToAddnlData2()));
                    elt.click();
                }

                Map<String, String> addlDataMap2 = base.getTextsBySiblingsText(settings.getAdditionalDataMap2(), ".//td[2]");
                allPerm.putAll(addlDataMap);
                allPerm.putAll(addlDataMap2);
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
