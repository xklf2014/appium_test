package appium.page;

import org.openqa.selenium.By;

public class FollowPage extends BasePage{
    //跳转到关注用户页面
    public FollowUserPage toFollowUserPage(){
        click(By.id("iv_add_user"));
        return new FollowUserPage();
    }


}
