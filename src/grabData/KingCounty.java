package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KingCounty {
    Base base;
    String driverBrowser = "Chrome";
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
        if(base.isElementOnPage(lots.get(3))){
            lot = base.getElement(lots.get(3),settings.getPathToLot(), "get lot");
            base.elementClick(lot, "click lot");
         //TODO replace on wait
            base.sleep(500);
            gatherDataPage();



        //driver.navigate().back();

        }


    }
        //private getAucInfo()
    private void gatherDataPage(){
        Map<String, String> permMap = base.getTexts(settings.getPermanentMap());
        Map<String, String> varMap = base.getTexts(settings.getVariableMap());
        String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String url = driver.getCurrentUrl();
        System.out.println(timeStamp);
        System.out.println(url);

    }

}
