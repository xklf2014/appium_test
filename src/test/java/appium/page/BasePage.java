package appium.page;

import appium.model.PageObectModel;
import appium.model.PageObjectElement;
import appium.model.PageObjectMethod;
import appium.test.TestSearch;
import appium.utils.DataDriverProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class BasePage {
    public static AndroidDriver driver;

    public static HashMap<String,Object> param = new HashMap<>();
    public static HashMap<String,Object> texts = new HashMap<>();
    private PageObectModel model = new PageObectModel();

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

    public void parseMethodStep(){
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(methodName);
        parseMethodStep(methodName);
    }

    public void parseMethodStep(String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String path = "/"+this.getClass().getCanonicalName().replace(".","/") + ".yaml";
        parseStep(path,method);
    }

    public  void parseStep(String path,String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
             model = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),PageObectModel.class);
            parseStep(model.methods.get(method));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseElements(){

    }

    private void parseStep(PageObjectMethod dataDriverProvider){
        dataDriverProvider.getDataProvider().forEach(step->{
            WebElement element = null;
            if (step.containsKey("id")){
                element = driver.findElement(By.id(step.get("id")));
            }else if(step.containsKey("xpath")){
                element = driver.findElement(By.xpath(step.get("xpath")));
            }else if(step.containsKey("aid")) {
                element = driver.findElement(MobileBy.AccessibilityId("aid"));
            }else if(step.containsKey("element")){
                //未完成
                //element = driver.findElement(model.elements.get(step.get("element")).getLocator());
            }

            if (element != null) {
                if (step.get("send") != null) {
                    String send = step.get("send");
                    //替换变量
                    for (Map.Entry<String,Object> kv : param.entrySet()){
                        String matcher = "${"+kv.getKey()+"}";
                        if (send.contains(matcher)){
                            System.out.println(kv.getKey() + "====>" + kv.getValue());
                            send = send.replace(matcher, kv.getValue().toString());
                        }
                    }
                    element.sendKeys(send);
                } else if (step.get("get") != null) {
                    String price = element.getAttribute(step.get("get"));
                    System.out.println("price====>"+price);
                    texts.put(step.get("dump"),price);
                } else {
                    element.click();
                }
            }
        });
    }



    public PageObjectElement dataProvider(Class caller){
        PageObjectElement elements = new PageObjectElement();
        String path = "/"+caller.getCanonicalName().replace(".","/") + ".yaml";
        System.out.println(path);
        List result = new ArrayList();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {

            elements = mapper.readValue(TestSearch.class.getResourceAsStream(path),PageObjectElement.class);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(elements));
            return elements;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        /*PageObjectElement elements = model.elements.get("locator");
        System.out.println(elements.getElement().toString());
        for (HashMap<String, String> hashMap : elements.getElement()) {
            System.out.println(hashMap.entrySet().stream());
            Set<String> mapset = hashMap.keySet();
            Iterator<String> itor = mapset.iterator();
            while (itor.hasNext()){
                HashMap<String,String> tmp = new HashMap<>();
                String key = itor.next();
                System.out.println(key + "====" + hashMap.get(key));
            }
        }*/

    }

   /* @Test
    public void parseTest() throws MalformedURLException {
        App.startUp();
        BasePage basePage = new BasePage();
        basePage.parseMethodStep("search");
    }*/

    public HashMap<String, Object> getParam() {
        return param;
    }

    public void setParam(HashMap<String, Object> param) {
        this.param = param;
    }

    public HashMap<String, Object> getTexts() {
        return texts;
    }
}
