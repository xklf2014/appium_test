package appium.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import javax.sql.XADataSource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static AndroidDriver driver;

    public static WebElement findElement(By by){
        try {
            return driver.findElement(by);
        }catch (Exception e){
            //递归处理弹窗
            handleAlert();
            return driver.findElement(by);
        }

    }

    public static void click(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            //递归处理弹窗
            handleAlert();
            driver.findElement(by).click();
        }

    }

    public static void click(WebElement element){
        try {
            element.click();
        }catch (Exception e){
            //递归处理弹窗
            handleAlert();
            element.click();
        }

    }

    //处理handle
    private static void handleAlert() {
        List<By> alertBoxs  = new ArrayList<>();
        alertBoxs.add(By.id("image_cancel"));
        alertBoxs.add(By.id("snb_tip_text"));

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        alertBoxs.forEach(alert -> {
            //By adsLocator = alert;
            List<WebElement> ads = driver.findElements(alert);

            if (ads.size()>0){
                if (alert.equals(By.id("snb_tip_text"))) {
                    Dimension size = driver.manage().window().getSize();
                    new TouchAction(driver).tap(PointOption.point(size.width/2,size.height/2));
                }else {
                    ads.get(0).click();
                }
            }
        });
    }

    //扩展
    public static void handleAlertByPageSource(){
        String pageSource = driver.getPageSource();
        //List<HashMap<String,By>> handles = new ArrayList<HashMap<String,By>>();
        //HashMap<String,WebElement> handlebox = new HashMap<String,WebElement>();
        List<String> alertBoxs = new ArrayList<>();
        alertBoxs.add("aaa");
        alertBoxs.add("bbb");

        alertBoxs.forEach(alert->{
            if (pageSource.contains(alert)){
                driver.findElement(By.id(alert)).click();
            }
        });

    }
}
