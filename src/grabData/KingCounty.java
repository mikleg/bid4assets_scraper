package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
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
        lots = base.getElements(aucs.get(4), settings.getPathToLots(), " get lots");
        System.out.println("debug:" + lots.size());
        if(base.isElementOnPage(lots.get(4))){
          lot = base.getElement(lots.get(4),"//*[" + 4 + "]" + settings.getPathToLot(), "get lot");
            base.elementClick(lot, "click lot");
           // WebElement lotik = lots.get(4).findElement(By.xpath("//*[4]/td[3]/a"));
            //lotik.click();
////*[@id="auctionGrid-2875"]/table/tbody/tr[4]/td[3]
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
        Map<String, String> permMap = base.getTexts(settings.getPermanentMap());
        Map<String, String> varMap = base.getTexts(settings.getVariableMap());
        Map<String, String> linksMap = base.getLinks(settings.getLinksMap());
        String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String url = driver.getCurrentUrl();
        System.out.println(timeStamp);
        System.out.println(url);
        driver.navigate().to("chrome://settings/content/pdfDocuments?search=pdf");
        base.sleep(1500);
        driver.navigate().to(linksMap.get("titleReport"));
        base.sleep(1500);

/*        try {
            testChromeDownloadPopup();
        }
        catch(InterruptedException ie){
        }*/

        /*if(base.isElementOnPage(By.xpath("*[@id=\"download\"]"))){
            driver.findElement(By.xpath("*[@id=\"download\"]")).;
        }*/
        ////*[@id="icon"]
        // //*[@id="download"]

    }
    /* Change option to not show pdf in browser directly. */
    private void setChromeOptions(ChromeDriver cDriver)
    {
		/* Go to Chrome configure options page. */
        cDriver.get("chrome://settings-frame/content");

		/* Find the pdf configure section input checkbox.*/
        By pdfSectionBy = By.id("pdf-section");
        WebElement pdfSectionElement = cDriver.findElement(pdfSectionBy);
		/* Find checkbox in configure section. */
        By inputBy = By.tagName("input");
        WebElement pdfSectionInput = pdfSectionElement.findElement(inputBy);

		/* If not checked then check it. */
        if(!pdfSectionInput.isSelected())
        {
            pdfSectionInput.click();
        }
    }


    public void testChromeDownloadPopup() throws InterruptedException {

		/* Set string variable value to ChromeDriver executable file path.*/
        String chromeDriverPath = settings.getExePath();
		/* Assign chromeDriverPath to system property "webdriver.chrome.driver" */
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        Map<String, Object> chromePreferences = new Hashtable<String, Object>();
		/* Below two chrome preference settings will disable popup dialog when download file.*/
        chromePreferences.put("profile.default_content_settings.popups", 0);
        chromePreferences.put("download.prompt_for_download", "false");

		/* Set file save to directory. */
        chromePreferences.put("download.default_directory", "C:\\WorkSpace");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePreferences);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        //Initiate ChromeDriver
        ChromeDriver cDriver = new ChromeDriver(cap);


		/* For tomcat 9.0.zip. */
        //cDriver.get("http://mirror.nexcess.net/apache/tomcat/tomcat-9/v9.0.0.M22/bin/apache-tomcat-9.0.0.M22.zip");


		/* For pdf.
		 * First check not show pdf in Chrome browser.
		 * */
        this.setChromeOptions(cDriver);
        cDriver.get("https://docs.oracle.com/javaee/7/JEETT.pdf");

        System.out.println("Task complete, please go to save folder to see it.");
    }
}
