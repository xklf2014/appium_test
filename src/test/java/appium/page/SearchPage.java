package appium.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class SearchPage extends BasePage{
    private By inputBox = By.id("search_input_text");

    public SearchPage search(String keyWord){
        driver.findElement(inputBox).sendKeys(keyWord);
        //click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]"));
        click(By.id("name"));
        //findElementAndClick(By.xpath("//*[@id='listview']/RelativeLayout[0]"));
        return this;
    }

    public Float getCurrentPrice(){
        return Float.valueOf(driver.findElementById("current_price").getText());
    }

    public App cancel(){
        click(By.id("action_close"));
        return new App();
    }
}
