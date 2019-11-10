package appium.page;

import org.openqa.selenium.By;

public class LoginByPhonePage extends BasePage{
    AccountPage accountPage = new AccountPage();
    //手机和验证码登录
    public AccountPage login(String phone,String verify){
        click(By.id("register_phone_number"));
        findElement(By.id("register_phone_number")).sendKeys(phone);
        click(By.id("register_code_text"));
        click(By.id("register_code"));
        findElement(By.id("register_code")).sendKeys(verify);
        click(By.id("button_next"));
        accountPage.setLoginResultContent(findElement(By.id("md_content")).getText());
        return accountPage;
    }

    public AccountPage backToAccount(){
        click(By.id("iv_action_back"));
        return accountPage;
    }
}
