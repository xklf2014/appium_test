package selenium.testcase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import selenium.enums.DriverEnum;
import selenium.pages.HomePage;

import static java.lang.Thread.sleep;

public class TestWeWork {
    public static String url = "https://work.weixin.qq.com/";
    //static WebDriver driver;
    static HomePage homePage;

    @Before
    public void setUp() {
        homePage = new HomePage();
        homePage.initialization(DriverEnum.fIREFOX_DRIVER,url);
    }

    @After
    public void tearDown() throws InterruptedException {
        sleep(5);
        //driver.quit();
    }



   @Test
   public void testLogin(){
       //BasePage.driver = driver;
       homePage.goToLoginPage().goToContactPage()
               .addOneContactPerson("tester1","098754","13511111112")
               .searchOnePersonAndDel("13511111112");

/*
       ContactPage contactPage = new ContactPage();
       contactPage.addOneContactPerson("tester1","098754","13511111112");
        contactPage.searchOnePersonAndDel("13511111112");*/
   }


}
