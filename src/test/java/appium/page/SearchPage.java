package appium.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class SearchPage extends BasePage{
    private By inputBox = By.id("com.xueqiu.android:id/search_input_text");

    public SearchPage search(String keyWord){
        driver.findElement(inputBox).sendKeys(keyWord);
        findElementAndClick(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]"));
        return this;
    }

    public Float getCurrentPrice(){
        return Float.valueOf(driver.findElementById("com.xueqiu.android:id/current_price").getText());
    }

    public App cancel(){
        findElementAndClick(By.id("com.xueqiu.android:id/action_close"));
        return new App();
    }
}
