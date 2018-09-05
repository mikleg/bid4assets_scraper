package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        //TODO check all data fields
        List<WebElement> aucs = base.getListByXpath(settings.getPathToAucs(), " get aucs");
        base.elementClick(aucs.get(3), "auc click:" );
        lots = base.getElements(aucs.get(3), settings.getPathToLots(), " get lots");
        System.out.println("debug:" + lots.size());
        if(base.isElementOnPage(lots.get(3))){
            lot = base.getElement(lots.get(3),settings.getPathToLot(), "get lot");
            base.elementClick(lot, "click lot");
            base.sleep(500);

            Map<String,String> permMap = settings.getPermanentMap();
            for (Map.Entry<String, String> entry : permMap.entrySet()) {
                String data = base.getText(entry.getValue(),entry.getKey());
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue() + " Data: " + data);
            }
        //driver.navigate().back();

        }


    }
        //private getAucInfo()


}
