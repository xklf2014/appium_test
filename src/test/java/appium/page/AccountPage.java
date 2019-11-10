package appium.page;

import org.openqa.selenium.By;

public class AccountPage extends BasePage{


    //从我的页面切换到手机登录页面
    public LoginByPhonePage toLoginByPhone(){
        click(By.id("rl_login_phone"));
        return new LoginByPhonePage();
    }

    public App backToApp(){
        click(By.id("action_back"));
        return new App();

    }
}
