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
        List<WebElement>  aaa= getAucs(driver, mainUrl, pathToPlus, pathToAucs);
       // WebDriverWait wait = new WebDriverWait(driver, 5);
      //  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//*[@onclick=\"loadAuctionsForCollection(2881,10)\"]"))));
        WebElement auc = driver.findElement(By.xpath(".//*[@onclick=\"loadAuctionsForCollection(2881,10)\"]"));
        auc.click();

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
    private static List<WebElement> getAucs(WebDriver myDriver, String url, String myPathToPlus, String myPathToAucs){
        myDriver.get(url);
        WebElement plus = myDriver.findElement(By.xpath(myPathToPlus));
        //click on +
        plus.click();
        WebDriverWait wait = new WebDriverWait(myDriver, 5);
        wait.until(ExpectedConditions.visibilityOf(myDriver.findElement(By.xpath(myPathToAucs))));
        List<WebElement> aucs =  myDriver.findElements(By.xpath(myPathToAucs));
        return aucs;


        //TODO  check for visibility

    }

}
