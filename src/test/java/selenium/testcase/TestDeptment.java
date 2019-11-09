package selenium.testcase;

import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.enums.DeptmentOperationEnum;
import selenium.enums.DriverEnum;
import selenium.pages.BasePage;
import selenium.pages.HomePage;
import selenium.pages.contact.ContactPage;
import selenium.pages.contact.SubDeptment;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TestDeptment {
    public static String url = "https://work.weixin.qq.com/";
    //static WebDriver driver;
    static HomePage homePage;
    static ContactPage contactPage;

    @BeforeClass
    public static void setUp() {
        homePage = new HomePage();
        homePage.initialization(DriverEnum.fIREFOX_DRIVER,url);
        contactPage = homePage.goToLoginPage().goToContactPage();
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        BasePage.driver.quit();
    }

    @Test
    public void test_01_AddDept(){
        SubDeptment subDept = contactPage.searchSubDeptmentByDeptName("2","测试部");
        if (subDept == null){
            subDept = new SubDeptment();
            subDept.addSubDeptment("测试部");
        }
        assertThat(contactPage.returnDepetNameByGradeAndDeptName("2","测试部"), is("测试部"));
    }


    @Test
    public void del_02_Dept(){
        contactPage.searchSubDeptmentByDeptName("2","测试部").operatorSubDeptmentByDeptName("2","测试部", DeptmentOperationEnum.DEL,"");
    }

    @Test
    public void test_03_UpGrafeDept(){
        contactPage.searchSubDeptmentByDeptName("2","2").operatorSubDeptmentByDeptName("2","2", DeptmentOperationEnum.UP,"");
    }

    @Test
    public void test_04_DownGrafeDept(){
        contactPage.searchSubDeptmentByDeptName("2","2").operatorSubDeptmentByDeptName("2","2", DeptmentOperationEnum.DOWN,"");
    }

    @Test
    public void test_05_UpdateDeptName(){
        contactPage.searchSubDeptmentByDeptName("2","5").operatorSubDeptmentByDeptName("2","5", DeptmentOperationEnum.UPDATE,"newName");
    }
}
