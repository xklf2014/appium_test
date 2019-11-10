package appium.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App extends BasePage{
    public static WebElement xueqiuBtn;
    public static WebElement selfChooseBtn;
    public static WebElement followBtn;
    public static WebElement marketBtn;
    public static WebElement dealBtn;


    public static void startUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "app");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity",".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("autoGrantPermissions",true);
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //new WebDriverWait(driver,30).until()


    }

    public static void initNavigationBtn(){
        List<WebElement> navigationBtns = driver.findElements(By.id("tab_name"));
        xueqiuBtn = navigationBtns.get(0);
        selfChooseBtn = navigationBtns.get(1);
        followBtn = navigationBtns.get(2);
        marketBtn = navigationBtns.get(3);
        dealBtn = navigationBtns.get(4);

    }

    public static SearchPage toSearch(){
        click(By.id("tv_search"));
        return new SearchPage();

    }

    public static StockPage toStockPage(){
        click(selfChooseBtn);
        return new StockPage();

    }

    public static AccountPage toAccountPage(){
        click(findElement(By.id("user_profile_icon")));
        return  new AccountPage();
    }

}
