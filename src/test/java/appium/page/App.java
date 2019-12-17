package appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App extends BasePage{
    public static WebElement xueqiuBtn;
    public static WebElement selfChooseBtn;
    public static WebElement followBtn;
    public static WebElement marketBtn;
    public static WebElement dealBtn;

    private static App app;

    public static App getAppInstance(){
        if (app == null){
            app = new App();
        }
        return app;
    }


    public static void startUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "appium");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity",".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("autoGrantPermissions",true);
        desiredCapabilities.setCapability("udid",System.getenv("udid"));
        URL remoteUrl = new URL("http://192.168.2.116:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        new WebDriverWait(driver,30).until(x-> {
            boolean flag = false;
            long begin = System.currentTimeMillis();
            String xml = driver.getPageSource();
            flag = xml.contains("home_search")||xml.contains("image_cancel");
            System.out.println((System.currentTimeMillis()-begin)/1000);
            return flag;

        });

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

    public SearchPage toSearch(){
        //click(By.id("tv_search"));
        parseStep("/appium/page/App.yaml","toSearch");
        return new SearchPage();

    }

    public StockPage toStockPage(){
        //click(selfChooseBtn);
        parseStep("/appium/page/App.yaml","toStockPage");
        return new StockPage();

    }

    public AccountPage toAccountPage(){
        //click(findElement(By.id("user_profile_icon")));
        parseStep("/appium/page/App.yaml","toAccountPage");
        return  new AccountPage();
    }

    public FollowPage toFollowPage(){
        //click(followBtn);
        parseStep("/appium/page/App.yaml","toFollowPage");
        return new FollowPage();
    }

}
