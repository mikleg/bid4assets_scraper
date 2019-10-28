package grabData;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
           // FirefoxProfile profile = new FirefoxProfile();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("marionette", true);
            //webdriver = new FirefoxDriver(firefoxOptions);

          //  FirefoxProfile profile = new FirefoxProfile();
            firefoxOptions.setCapability("browser.download.dir", "C:\\Utility\\Downloads");
            firefoxOptions.setCapability("browser.download.folderList",2);
            firefoxOptions.setCapability("browser.helperApps.neverAsk.saveToDisk", "text/plain,application/octet-stream,application/pdf,application/x-pdf,application/vnd.pdf");
            firefoxOptions.setCapability("browser.download.manager.showWhenStarting", false);
            firefoxOptions.setCapability("browser.helperApps.neverAsk.openFile","text/plain,application/octet-stream,application/pdf,application/x-pdf,application/vnd.pdf");
            firefoxOptions.setCapability("browser.helperApps.alwaysAsk.force", false);
            firefoxOptions.setCapability("browser.download.manager.useWindow", false);
            firefoxOptions.setCapability("browser.download.manager.focusWhenStarting", false);
            firefoxOptions.setCapability("browser.helperApps.neverAsk.openFile", "");
            firefoxOptions.setCapability("browser.download.manager.alertOnEXEOpen", false);
            firefoxOptions.setCapability("browser.download.manager.showAlertOnComplete", false);
            firefoxOptions.setCapability("browser.download.manager.closeWhenDone", true);
            firefoxOptions.setCapability("pdfjs.disabled", true);
           // System.setProperty("webdriver.firefox.bin", settings.getFfDriverPath());
            //WebDriver ddriver = new FirefoxDriver(profile);
            driver = new FirefoxDriver(firefoxOptions);
            



            //firefoxProfile.setPreference("browser.private.browsing.autostart",true);
           // FirefoxOptions opt = new FirefoxOptions();
           // driver = new FirefoxDriver(opt);
        }
        else {
            System.setProperty("webdriver.chrome.driver", settings.getExePath());

            //
           /* ChromeOptions options = new ChromeOptions();
            Map<String, Object> preferences = new Hashtable<String, Object>();
            options.setExperimentalOption("prefs", preferences);

// disable flash and the PDF viewer
            preferences.put("plugins.plugins_disabled", new String[] {
                    "Chrome PDF Viewer"
            });*/


           // driver = new ChromeDriver(options);
            driver = new ChromeDriver();





            //

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
            sleep(minSleepTime*100);
            aucs = null;
        }
        return aucs;
    }

    //ZAKONCHIL ZDES
    public List<WebElement> getListByClass(String weclass, String addmInf){
        sleep(minSleepTime*100);
        int initialSize = driver.findElements(By.className(weclass)).size();
        List<WebElement> aucs;
        if (isElementOnPage(By.className(weclass))){
            aucs = driver.findElements(By.className(weclass));
            return aucs;
        }

        return null;
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

    String getTextOfSibling(String xPath, String pathToSibling, String info){
        if (isElementOnPage(By.xpath(xPath))){
            System.out.println("debug xp=" + xPath);
            WebElement el = driver.findElement(By.xpath(xPath));

            WebElement parent = el.findElement(By.xpath(".."));
            //divA.findElement(By.xpath(".//input"));
            if (isElementOnPage(parent.findElement(By.xpath(pathToSibling)))){
                WebElement child =  parent.findElement(By.xpath(pathToSibling));
                return child.getText();
            }
            else
            {System.out.println("an child element with Xpath= " + xPath + pathToSibling + " wasn't founded. getTextOfSibling = " + info);}

        }
        else{
            System.out.println("an element with Xpath= " + xPath + " wasn't founded. getTextOfSibling = " + info);
        }
        return "None";
    }

    String getTextOfSiblingsText(String text, String pathToSibling, String info){
        String xPath = "//*[contains(text(),'" + text + "')]";
        System.out.println("debug getTextOfSiblingsText xp=" + xPath);
        return getTextOfSibling(xPath, pathToSibling, info);
    }

    WebElement getElementByText(String text, String info){
        String xPath = "//*[contains(text(),'" + text + "')]";
        if (isElementOnPage(By.xpath(xPath))){
            return driver.findElement(By.xpath(xPath));
        }
        else{
            System.out.println("an element with text= " + text + " wasn't founded. getElementByText = " + info);
            return null;
        }
    }




    String getLink(String xPath, String info){
        if (isElementOnPage(By.xpath(xPath))){
            WebElement el = driver.findElement(By.xpath(xPath));
            return el.getAttribute("href");
        }
        else{
            System.out.println("an element with Xpath= " + xPath + " wasn't founded. getLink = " + info);
            return "None";
        }
    }

    Map<String, String> getTexts(Map<String, String> xpaths){
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : xpaths.entrySet()) {
            String data = getText(entry.getValue(),entry.getKey());
            result.put(entry.getKey(), data);
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue() + " Data: " + data);
        }
        return  result;
    }

    Map<String, String> getTextsBySiblingsText(Map<String, String> xpaths, String sibling){
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : xpaths.entrySet()) {

            String data = getTextOfSiblingsText(entry.getValue(), sibling, entry.getKey());
            result.put(entry.getKey(), data);
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue() + " Data: " + data);
        }
        return  result;
    }

    Map<String, String> getLinks(Map<String, String> xpaths){
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : xpaths.entrySet()) {
            String data = getLink(entry.getValue(),entry.getKey());
            result.put(entry.getKey(), data);
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue() + " Data: " + data);
        }
        return  result;
    }

}