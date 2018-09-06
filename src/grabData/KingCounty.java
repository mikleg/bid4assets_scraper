package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class KingCounty {
    Base base;
    String driverBrowser = "chrome";
    WebDriver driver;
    Settings settings;

    public void runScraper(){
        base = new Base();
        driver = base.getNewDriver(driverBrowser);
        settings = new Settings();
        driver.get(settings.getMainUrl());
      //local//
        WebElement plus;
        List<WebElement> lots;
        WebElement lot;
      //end local//
        if (base.isElementOnPage(By.xpath(settings.getPathToPlus()))){
            plus = driver.findElement(By.xpath(settings.getPathToPlus()));
            base.elementClick(plus, "plus");
        }
        //--doneTODO Check length before click
        // TO DO check all data fields
        //TO DO ADD gathering links
        //TODO Add gathering docs
        //TODO Add gathering screensoots
        //TO DO timestamps
        List<WebElement> aucs = base.getListByXpath(settings.getPathToAucs(), " get aucs");
        base.elementClick(aucs.get(3), "auc click:" );
        lots = base.getElements(aucs.get(3), settings.getPathToLots(), " get lots");
        System.out.println("debug:" + lots.size());
        if(base.isElementOnPage(lots.get(1))){
            ////*[@id="auctionGrid-2875"]/table/tbody/tr[1]/td[3]
            //*[@id="auctionGrid-2875"]/table/tbody/tr[1]/td[3]
          lot = base.getElement(lots.get(1),"//*[" + 1 + "]" + settings.getPathToLot(), "get lot");
            base.elementClick(lot, "click lot");
            gatherDataPage();



        //driver.navigate().back();

        }


    }
        //private getAucInfo()
    private void gatherDataPage(){
        //TO DO replace on wait
        base.sleep(500);
        if(!base.isElementOnPage(By.xpath(settings.getPathToLastElement()))){
            base.sleep(3500);
           // base.isElementOnPage(By.xpath(settings.getPathToLastElement()));
        }
       // Map<String, String> permMap = base.getTexts(settings.getPermanentMap());
       // Map<String, String> varMap = base.getTexts(settings.getVariableMap());
        Map<String, String> linksMap = base.getLinks(settings.getLinksMap());
        String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String url = driver.getCurrentUrl();
        System.out.println(timeStamp);
        System.out.println(url);
        driver.navigate().to(linksMap.get("assessorInfo"));
        base.sleep(2500);
       // Map<String, String> addlDataMap = base.getTextsBySiblingsText(settings.getAdditionalDataMap(), ".//td[2]");
        if(base.isElementOnPage(By.xpath(settings.getPathToAddnlData2()))){
            WebElement elt = driver.findElement(By.xpath(settings.getPathToAddnlData2()));
            elt.click();
        }
       // Map<String, String> addlDataMap2 = base.getTextsBySiblingsText(settings.getAdditionalDataMap2(), ".//td[2]");
        Db.createNewDatabase("test.db");
        //TODO replace sleep
        //http://www.sqlitetutorial.net/sqlite-java/



    }

}
