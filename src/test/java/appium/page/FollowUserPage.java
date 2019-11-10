package appium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FollowUserPage extends BasePage{

    //关注前n位用户，后续可扩展（例如根据姓名进行关注）
    public FollowUserPage  followTopUsers(int exitException){
        //click(By.id("follow_btn"));
        List<WebElement> results = new ArrayList<>();
        results = findElements(By.id("follow_btn"));

        int i;
        for (i = 0;i < exitException; i++){
            click(results.get(i));   //查找速度过慢
            //results.get(i).click();
            click(By.id("md_buttonDefaultPositive"));
        }
        return this;
    }

    public FollowPage backToFollowPage(){
        click(By.className("android.widget.ImageButton"));
        return new FollowPage();
    }

    public int findFollowedUsers(){
        return findElements(By.id("followed_btn")).size();
    }

}
