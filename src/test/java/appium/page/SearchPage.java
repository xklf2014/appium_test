package appium.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.HashMap;

public class SearchPage extends BasePage{
    //private By inputBox = By.id("search_input_text");

    public SearchPage search(String keyWord){
        //driver.findElement(inputBox).sendKeys(keyWord);
       // click(By.id("name"));
        HashMap<String,Object> data = new HashMap<>();
        data.put("keyword",keyWord);
        setParam(data);
        parseMethodStep();
        return this;
    }

    public Float getCurrentPrice(){
        parseMethodStep();
        //return Float.valueOf(driver.findElementById("current_price").getText());
        return Float.valueOf(getTexts().get("price").toString());
    }

    public App cancel(){
        parseMethodStep();
        //click(By.id("action_close"));
        return new App();
    }
}
