package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GatherData {
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

        base.coockeClick(driver); //ckick on cook
/*        WebElement cook = driver.findElement(By.cssSelector("a[aria-label='dismiss cookie message']"));
        if (cook.isDisplayed()) {
            cook.click();
            base.sleep(1000);
        }*/
        //---- if (auctions folder) collapsed, click it
        WebElement plus2 = driver.findElement(By.cssSelector("a[href='#collapseFive']")); //second level menu // System.out.println("debug "  + plus2.getAttribute("aria-expanded")); //debug
            if (plus2.getAttribute("aria-expanded").equals("false"))  // click if not expanded
                {
                    base.elementClick(plus2, "auc click:");//           System.out.println("debug "  + aucExpanded); //debug
                    base.sleep(1800);
                }
         //--- open all auction folders
        articles = base.getListByClass("ac-large", "no info");
        for (int k=0; k < articles.size(); k++) {
            List<WebElement> labels = base.getListByCSS(".bsnone.pl50", "no info"); //System.out.println("debug labels" + labels.get(k)); //debug
           // base.sleep(1800);
            articles = base.getListByClass("ac-large", "no info"); //System.out.println("debug articles" + articles.get(k)); //debug
           // base.sleep(1800);
            if (articles.get(k).getAttribute("style").equals("height: 0px;")|| articles.get(k).getAttribute("style").equals("")) {// click if not expanded
                Actions actions = new Actions(driver);
                actions.moveToElement(labels.get(k),0,-15).perform();
                base.sleep(800);
                base.elementClick(labels.get(k), "auc click:"); //if auction folder is closed -- open it
                base.sleep(1800);
            }
            List<WebElement> badRefs = new ArrayList<>();
            WebElement el = articles.get(k);
            badRefs = el.findElements(By.tagName("a"));
            //------delete all ref with class k-link
            for (WebElement ref : badRefs) {
                if (!ref.getAttribute("class").equals("k-link")){
                    refs.add(ref);
                allLots.add(ref.getAttribute("href"));
                 }
            }
         }

        int debug4=1;
        driver.close();
     //  gatherDataPage(allLots.get(1), true);
        for (String link:
             allLots) {
            gatherDataPage(link, true);

        }
    }

    private void gatherDataPage(String aucUrl, boolean useAddl){
        //TO DO replace on wait
        driver = base.getNewDriver(driverBrowser);
        driver.get(aucUrl);
        base.sleep(800);
        base.coockeClick(driver); //ckick on cook
      //  if(!base.isElementOnPage(By.xpath(settings.getPathToLastElement()))){
        //    base.sleep(3500);
          // }
        Map<String, String> varMap = base.getTexts(settings.getVariableMapPierce());
       //
      //  Map<String, String> varMap2 = base.getTextsBySiblingsText(settings.getVar2Map(), ".//td");
     //   varMap.putAll(varMap2);
        Map<String, String> shortMap = base.getTexts(settings.getShortMap());
     //   if (varMap.get("auctionType").equals("None")){
            Map<String, String> permMap = base.getTexts(settings.getPermanentMap());
            Map<String, String> linksMap = base.getLinks(settings.getLinksMapPierce());
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
              // allPerm.putAll(addlDataMap);debug
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
