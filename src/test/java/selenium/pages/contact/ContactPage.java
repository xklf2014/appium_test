package selenium.pages.contact;

import org.openqa.selenium.By;
import selenium.pages.BasePage;

public class ContactPage extends BasePage {
    public ContactPage addOneContactPerson(String uname,String accountId,String phone){
        findElement(By.linkText("添加成员"),3).click();
        findElement(By.id("username"),3).sendKeys(uname);
        findElement(By.id("memberAdd_acctid"),3).sendKeys(accountId);
        findElement(By.id("memberAdd_phone"),3).sendKeys(phone);
        findElement(By.linkText("保存并继续添加"),3).click();
        return this;
    }

    public ContactPage searchOnePersonAndDel(String phone){
        findElement(By.id("memberSearchInput"),3).sendKeys(phone);
        findElement(By.linkText("删除"),3).click();
        findElement(By.linkText("确认"),3).click();
        //driver.findElement(By.id("clearMemberSearchInput")).click();
        return this;
    }



    public SubDeptment searchSubDeptmentByDeptName(String grade,String deptName) {
        try{
            if (findElement(By.xpath("//li[@aria-level='" + grade + "']/child::a[contains(text(),'" + deptName + "')]"), 3).getText() != null) {
                findElement(By.xpath("//li[@aria-level='" + grade + "']/child::a[contains(text(),'" + deptName + "')]"), 3).click();
                return new SubDeptment();
            }
        }catch (Exception e){
            System.out.println("当前函数名称： searchSubDeptmentByDeptName");
            System.out.println("当前元素不存在：" + deptName);
        }
        return null;
    }

    public String returnDepetNameByGradeAndDeptName(String grade,String deptName){
        try {
            if (findElement(By.xpath("//li[@aria-level='" + grade + "']/child::a[contains(text(),'" + deptName + "')]"), 3).isDisplayed()) {
                return findElement(By.xpath("//li[@aria-level='" + grade + "']/child::a[contains(text(),'" + deptName + "')]"), 3).getText();
            }
        }catch (Exception e){
            System.out.println("当前函数名称： returnDepetNameByGradeAndDeptName");
            System.out.println("当前部门不存在："+ deptName);
        }
        return null;
    }


}
