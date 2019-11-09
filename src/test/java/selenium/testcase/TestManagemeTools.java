package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.enums.DriverEnum;
import selenium.pages.BasePage;
import selenium.pages.HomePage;
import selenium.pages.manageTools.ManageTools;

import java.awt.*;

public class TestManagemeTools {
    public static String url = "https://work.weixin.qq.com/";
    static HomePage homePage;
    static ManageTools manageTools;
    String picPath = "C:/Users/Administrator/Desktop/timg.jpg";

    @BeforeClass
    public static void setUp() {
        homePage = new HomePage();
        manageTools = new ManageTools();
        homePage.initialization(DriverEnum.CHROME_DRIVER,url);
        homePage.goToLoginPage();
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        BasePage.driver.quit();
    }

    //素材库-文字
    @Test
    public void test_01_addLetter() throws InterruptedException, AWTException {
        homePage.goToManageTools();
        manageTools.addLetter("hello , first add letter");
    }

    //素材库-图片
    @Test
    public void test_02_addPic() {
        homePage.goToManageTools();
        manageTools.addPicture(picPath);
    }

    //素材库-图文
    @Test
    public void test_03_addLetterAndPic() {
        homePage.goToManageTools();
        manageTools.addLetterAndPicture("title","this is content",picPath,picPath,"summary","steven");
    }
}
