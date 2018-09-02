package grabData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Main {
    private static int sTimeout  = 5; //timeout in sec for wait

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        String exePath = "C:\\Users\\mikle\\lc101\\auc_java\\chromedriver.exe";
        String ffDriverPath = "C:\\Users\\mikle\\lc101\\auc_java\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver",ffDriverPath);
        System.setProperty("webdriver.chrome.driver", exePath);
        WebDriver driver = new ChromeDriver();
        String mainUrl = "https://secure.bid4assets.com/mvc/storefront/KingWASept18";
        String pathToPlus = "/html/body/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/h4/a";
        String pathToAucs = "//*[@id=\"folderListView\"]/div";
        String pathToLots = "//*/table/tbody/tr";
        String pathToLot = "//*/td[3]/a";
       // FirefoxProfile firefoxProfile = new FirefoxProfile();
       // firefoxProfile.setPreference("browser.private.browsing.autostart",true);
       // FirefoxOptions opt = new FirefoxOptions();
        //WebDriver driver = new FirefoxDriver(opt);

        //driver.manage().deleteAllCookies();
        //driver.get("https://secure.bid4assets.com/mvc/storefront/KingWASept18?col=2881");
        driver.get(mainUrl);
        //click on some auction
        WebElement plus = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/h4/a"));
        plus.click();
        List<WebElement>  auctions= getAucs(driver, mainUrl, pathToPlus, pathToAucs);
        WebElement auction = auctions.get(0);
       //TO DO add wait in get lots /OK
        auction.click();

        List<WebElement> lots = getLots(driver, auction, pathToLots);

      //  aucs.get(0).findElement(By.xpath());
       // WebDriverWait wait = new WebDriverWait(driver, 5);
      //  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//*[@onclick=\"loadAuctionsForCollection(2881,10)\"]"))));
      //  WebElement auc = driver.findElement(By.xpath(".//*[@onclick=\"loadAuctionsForCollection(2881,10)\"]"));
      //  auc.click();
        clickLot(driver, lots.get(0), pathToLot); //works only in debug
        List els =  driver.findElements(By.xpath(".//*[@id=\"collapseFive\"]/div/div/div"));
        List els2 =  driver.findElements(By.xpath(".//*[@id=\"auctionGrid-2881\"]/table/tbody/tr[2]/td[3]/a"));
        List<WebElement> rows =  driver.findElements(By.xpath(".//*[@id=\"auctionGrid-2881\"]/table/tbody/tr"));
        WebElement someRow = rows.get(1);
        WebElement someAuc = someRow.findElement(By.xpath(".//td[3]/a"));

        someAuc.click();

        int debug = 0;

      //IT WORKS!
       // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//*[@id=\"auctionGrid-2881\"]"))));
       // WebElement table =  driver.findElement(By.xpath(".//*[@id=\"auctionGrid-2881\"]"));
      //  int rowNum = driver.findElements(By.xpath("//*[@id=\"auctionGrid-2881\"]/table/tbody/tr[1]/th")).size();
      //  System.out.println(rowNum);

       // driver.find_element_by_xpath("//button[@class='btn btn-primary' and @id='YesBtn'][@onclick=\"check_security('wlan1security_div0')\"]").click();
    }
    //returns a list of auctions
    private static void waitUntilLoad(WebDriver myDriver, String myPath){
        WebDriverWait wait = new WebDriverWait(myDriver, sTimeout);
        wait.until(ExpectedConditions.visibilityOf(myDriver.findElement(By.xpath(myPath))));
    }



    //TODO add try
    private static List<WebElement> getAucs(WebDriver myDriver, String url, String myPathToPlus, String myPathToAucs){
        myDriver.get(url);
        WebElement plus = myDriver.findElement(By.xpath(myPathToPlus));
        //click on +
        plus.click();
        waitUntilLoad(myDriver, myPathToAucs);
        List<WebElement> aucs =  myDriver.findElements(By.xpath(myPathToAucs));
        return aucs;


    }

    private static List<WebElement> getLots(WebDriver myDriver, WebElement auction, String myPathToLots){
       // List<WebElement> lots = auction.findElements(By.xpath(myPathToLots));

    //    String path = myPathToLots + "[1]";
     //   WebElement debug = auction.findElement(By.xpath(path));
        ////*[@id="auctionGrid-2873"]/table/tbody/tr[1]
        WebDriverWait wait = new WebDriverWait(myDriver, sTimeout);

      //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
        String aucPath = getXpath(auction);
        System.out.println(aucPath);
        String newPath = aucPath + myPathToLots;

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newPath)));
        List<WebElement> lots = auction.findElements(By.xpath(myPathToLots));
      //  wait.until(ExpectedConditions.visibilityOf(auction.findElement(By.xpath( myPathToLots))));
        ////*[@id="folderListView"]/div[1]/label  //*[@id="folderListView"]/div[1]/label
        ////*[@id="auctionGrid-2872"]/table/tbody/tr[1]
        //
        //#auctionGrid-2872 > table > tbody > tr:nth-child(1)
        return lots;
    }
    private static void clickLot(WebDriver myDriver, WebElement row, String myPathToLot){

        WebElement lot = row.findElement(By.xpath(myPathToLot));
        lot.click();
        ////*[@id="auctionGrid-2872"]/table/tbody/tr[3]/td[3]/a


    }

    private static String getXpath(WebElement ele) {
        String str = ele.toString();
        String[] listString;
        String[] bug = new String[1];
        bug[0] = "BUG";
        if(str.contains("xpath"))
            listString = str.split("xpath:");
        else if(str.contains("id"))
            listString = str.split("id:");
        else listString = bug;

        String last = listString[1].trim();
        return last.substring(0, last.length() - 1);
    }
}
