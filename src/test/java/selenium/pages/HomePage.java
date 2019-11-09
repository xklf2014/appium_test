package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import selenium.enums.DriverEnum;
import selenium.pages.contact.ContactPage;
import selenium.pages.manageTools.ManageTools;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{
    public HomePage initialization(Enum driveType,String url){
        if (driveType.equals(DriverEnum.fIREFOX_DRIVER)){
            System.setProperty("webdriver.gecko.driver","C:/Users/Administrator/Desktop/TestJunit/src/main/java/selenium/drivers/geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("pageLoadStrategy","none");
            driver = new FirefoxDriver();
        }else if(driveType.equals(DriverEnum.CHROME_DRIVER)){
            System.setProperty("webdriver.chrome.driver","C:/Users/Administrator/Desktop/TestJunit/src/main/java/selenium/drivers/chromedriver.exe");
            ChromeOptions chromeOptions=new ChromeOptions();
            chromeOptions.setCapability("pageLoadStrategy","none");
            driver = new ChromeDriver();
        }
        driver.get(url);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return this;
    }

    public HomePage goToLoginPage(){
        findElement(By.linkText("企业登录"),3).click();
        this.loginWithCookie();
        return this;
    }

    public void loginWithCookie(){
        driver.manage().addCookie(new Cookie("wwrtx.refid","替换自己的cookie里的wwrtx.refid"));
        driver.manage().addCookie(new Cookie("wwrtx.sid","替换自己的cookie里的wwrtx.sid"));

        driver.navigate().refresh();
    }

    public ContactPage goToContactPage(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(By.linkText("通讯录"),3).click();
        return new ContactPage();
    }

    public ManageTools goToManageTools(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(By.linkText("管理工具"),3).click();
        return new ManageTools();
    }
}
