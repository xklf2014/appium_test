package appium.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
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

    public static WebElement findCurrentElementFromList(By by){
        try {
            return driver.findElement(by);
        }catch (Exception e){
            //递归处理弹窗
            swpie(0.5,0.8,0.5,0.4);
            return findCurrentElementFromList(by);
        }

    }

    public static List<WebElement> findElements(By by){
        try {
            return driver.findElements(by);
        }catch (Exception e){
            //递归处理弹窗
            handleAlert();
            return driver.findElements(by);
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
        alertBoxs.add(By.id("ib_close"));
        alertBoxs.add(By.id("md_buttonDefaultPositive"));

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

    //滑动
    public static void swpie(Double beginX,Double beginY,Double endX,Double endY){
        TouchAction touchAction = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        touchAction.press(PointOption.point((int)(size.width*beginX),(int)(size.height*beginY)));
        touchAction.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        touchAction.moveTo(PointOption.point((int)(size.width*endX),(int)(size.height*endY)));
        touchAction.release().perform();
    }
}
