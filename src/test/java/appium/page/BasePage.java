package appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.sql.XADataSource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BasePage {
    public static AndroidDriver driver;

    public WebElement findElement(By by){
        try {
            return driver.findElement(by);
        }catch (Exception e){
            //递归处理弹窗
            handleAlert();
            return driver.findElement(by);
        }

    }

    public static void findElementAndClick(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            //递归处理弹窗
            handleAlert();
            driver.findElement(by).click();
        }

    }

    //处理handle
    private static void handleAlert() {
        List<By> alertBoxs  = new ArrayList<>();
        alertBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));

        alertBoxs.forEach(alert -> {
            By adsLocator = alert;
            List<WebElement> ads = driver.findElements(adsLocator);
            if (ads.size()>0){
                ads.get(0).click();
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
