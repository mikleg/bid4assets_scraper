package grabData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.PrivateKey;

public class Settings {
    private String crashMessage = "Crash in:";
    private String exePath = "C:\\Users\\mikle\\lc101\\auc_java\\chromedriver.exe";;
    private String ffDriverPath = "C:\\Users\\mikle\\lc101\\auc_java\\geckodriver.exe";
    private String mainUrl = "https://secure.bid4assets.com/mvc/storefront/KingWASept18";
    private String pathToPlus = "/html/body/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/h4/a";
    private String pathToAucs = "//*[@id=\"folderListView\"]/div";
    private String pathToLots = "//*/table/tbody/tr";
    private String pathToLot = "//*/td[3]/a";

    public String getExePath() {
        return exePath;
    }

    public  String getFfDriverPath() {
        return ffDriverPath;
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public String getPathToPlus() {
        return pathToPlus;
    }

    public String getPathToAucs() {
        return pathToAucs;
    }

    public String getPathToLots() {
        return pathToLots;
    }

    public String getPathToLot() {
        return pathToLot;
    }

    public String getCrashMessage() {
        return crashMessage;
    }
}
