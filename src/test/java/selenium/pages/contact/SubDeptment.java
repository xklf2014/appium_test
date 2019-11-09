package selenium.pages.contact;

import org.openqa.selenium.By;
import selenium.enums.DeptmentOperationEnum;
import selenium.pages.BasePage;

public class SubDeptment extends BasePage {
     public SubDeptment addSubDeptment(String subDeptName){
        findElement(By.linkText("添加子部门"),3).click();
        findElement(By.name("name"),3).sendKeys(subDeptName);
        findElement(By.linkText("确定"),3).click();
        return this;
    }

    public SubDeptment operatorSubDeptmentByDeptName(String grade, String deptName, DeptmentOperationEnum deptmentOperationEnum,String newName){
        if (findElement(By.xpath("//li[@aria-level='"+grade+"']/child::a[contains(text(),'"+ deptName+"')]"),3).isDisplayed()){
            findElement(By.xpath("//li[@aria-level='"+grade+"']/child::a[contains(text(),'"+ deptName+"')]"),3).click();
            findElement(By.xpath("//li[@aria-level='"+grade+"']/child::a[contains(text(),'"+ deptName+"')]"),3)
                    .findElement(By.tagName("span")).click();
            if (deptmentOperationEnum.equals(DeptmentOperationEnum.DEL)){
                findElement(By.linkText("删除"),3).click();
                findElement(By.linkText("确定"),3).click();
            }else if (deptmentOperationEnum.equals(DeptmentOperationEnum.UP)){
                findElement(By.linkText("上移"),3).click();
            }else if(deptmentOperationEnum.equals(DeptmentOperationEnum.DOWN)){
                findElement(By.linkText("下移"),3).click();
            }else if(deptmentOperationEnum.equals(DeptmentOperationEnum.UPDATE)){
                findElement(By.linkText("修改名称"),3).click();
                findElement(By.name("name"),3).clear();
                findElement(By.name("name"),3).sendKeys(newName);
                findElement(By.linkText("保存"),3).click();
            }
            return new SubDeptment();
        }
        return null;
    }

}
