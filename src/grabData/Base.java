package grabData;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Base {
    private WebDriver driver;
    private int timeOut = 15;
    private int stdTimeOut = 2500;
    private int minSleepTime = 3;
    private Settings settings;
    private WebDriver newDriver(String browswerName){
        WebDriver driver;
        settings = new Settings();
        if (browswerName == "ff") {
            System.setProperty("webdriver.gecko.driver", settings.getFfDriverPath());
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.private.browsing.autostart",true);
            FirefoxOptions opt = new FirefoxOptions();
            driver = new FirefoxDriver(opt);
        }
        else {
            System.setProperty("webdriver.chrome.driver", settings.getExePath());
            driver = new ChromeDriver();
        }
        return driver;
    }

    private void setDriver(String type){
        driver = newDriver(type);
    }

    public WebDriver getNewDriver(String BrowserName) {
        setDriver(BrowserName);
        return driver;
    }

    protected void changeImplicitWait(int value, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(value, timeUnit);
    }

    protected void restoreDefaultImplicitWait() {
        changeImplicitWait(timeOut, TimeUnit.SECONDS);
    }

    protected boolean isElementOnPage(By by) {
        changeImplicitWait(stdTimeOut, TimeUnit.MILLISECONDS);
        boolean isElementOnPage = true;

        try {
            int size = driver.findElements(by).size();
            if (size <=0)
            {
                isElementOnPage = false;
                System.out.println("an element " + by.toString() + " wasn't founded. isElementOnPage(By by)");
                return driver.findElements(by).size() > 0;
            }
            else return driver.findElements(by).size() > 0;
        }


        finally {
            restoreDefaultImplicitWait();
        }


    }

    protected boolean isElementOnPage(WebElement element) {
        changeImplicitWait(stdTimeOut, TimeUnit.MILLISECONDS);
        boolean isElementOnPage = true;
        try {
            element.getLocation();
        } catch (WebDriverException ex) {
            isElementOnPage = false;
            System.out.println("an element " + element.toString() + " wasn't founded. isElementOnPage(WebElement element)");
        } finally {
            restoreDefaultImplicitWait();
        }
        return isElementOnPage;
    }

    public List<WebElement> getListByXpath(String path, String addnlInf) {
        List<WebElement> aucs;
        if (isElementOnPage(By.xpath(path))){
            int size;
            int initialSize = driver.findElements(By.xpath(path)).size();
            // System.out.println("debug sz =" + size);
            do {
                size = driver.findElements(By.xpath(path)).size();
                sleep(minSleepTime);
                if (size == driver.findElements(By.xpath(path)).size())
                {sleep(minSleepTime*10); System.out.println("debug sz10 =" + size);}
                if (size == driver.findElements(By.xpath(path)).size())
                {sleep(minSleepTime*100); System.out.println("debug sz100 =" + size);}
                if (size == driver.findElements(By.xpath(path)).size() && size == initialSize && initialSize < 2)
                {sleep(minSleepTime*1000); System.out.println("debug sz1000 =" + size);}
            } while(driver.findElements(By.xpath(path)).size() > size);


            aucs = driver.findElements(By.xpath(path));
        }
        else {
            System.out.println(settings.getCrashMessage() + addnlInf);
            aucs = null;
        }
        return aucs;
    }

    public void elementClick(WebElement el, String addnlInf){
        if (isElementOnPage(el)){
            el.click();
        }
        else System.out.println(settings.getCrashMessage() + addnlInf);
    }

    public WebElement getElement(WebElement el, String path, String addnlInf){
        if (isElementOnPage(el)&&isElementOnPage(el.findElement(By.xpath(path)))){
            return el.findElement(By.xpath(path));
        }
        else{
            System.out.println(settings.getCrashMessage() + addnlInf + "el: " + el.toString() + "with xpath: " + path);
            return null;
        }

    }

    public List<WebElement> getElements (WebElement el, String path, String addnlInf){
        if (isElementOnPage(el)&&isElementOnPage(el.findElement(By.xpath(path))))
        {    int size;
             int initialSize = el.findElements(By.xpath(path)).size();
            // System.out.println("debug sz =" + size);
             do {
                 size = el.findElements(By.xpath(path)).size();
                 sleep(minSleepTime);
                 if (size == el.findElements(By.xpath(path)).size())
                 {sleep(minSleepTime*10); System.out.println("debug sz10 =" + size);}
                 if (size == el.findElements(By.xpath(path)).size())
                 {sleep(minSleepTime*100); System.out.println("debug sz100 =" + size);}
                 if (size == el.findElements(By.xpath(path)).size() && size == initialSize)
                 {sleep(minSleepTime*1000); System.out.println("debug sz1000 =" + size);}

                // System.out.println("debug sz =" + size);
             } while(el.findElements(By.xpath(path)).size() > size);




            return el.findElements(By.xpath(path));
        }
        else{
            System.out.println(settings.getCrashMessage() + addnlInf + "el: " + el.toString() + "with xpath: " + path);
            return null;
        }

    }
     void sleep(int time){
        try{
            Thread.sleep(time);
        }
        catch(InterruptedException ie){
        }
    }

    String getText(String xPath, String info){
        if (isElementOnPage(By.xpath(xPath))){
            WebElement el = driver.findElement(By.xpath(xPath));
           return el.getText();
        }
        else{
            System.out.println("an element with Xpath= " + xPath + " wasn't founded. getText = " + info);
            return "None";
        }
    }


}