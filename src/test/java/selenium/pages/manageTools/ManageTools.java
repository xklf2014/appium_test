package selenium.pages.manageTools;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import selenium.pages.BasePage;

public class ManageTools extends BasePage {
    public ManageTools goToSourceLib(){
        findElement(By.xpath("//div[contains(text(),'素材库')]"),3).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ManageTools addLetter(String letter){
        this.goToSourceLib();
        findElement(By.linkText("添加文字"),3).click();
        findElement(By.tagName("textarea"),3).sendKeys(letter);
        findElement(By.linkText("选择"),3).click();
        findElement(By.xpath("//span[contains(text(),'公告')]"),3).click();
        findElement(By.linkText("确定"),3).click();
        findElement(By.linkText("保存"),3).click();
        return this;
    }

    public ManageTools addPicture(String picPath){
        this.goToSourceLib();
        findElement(By.linkText("图片"),3).click();
        findElement(By.linkText("添加图片"),3).click();
        findElement(By.id("js_upload_input"),0).sendKeys(picPath);
        //下列为AutoIt3录制上传图片操作，暂未经测试
        //findElement(By.xpath("//div[contains()text(),'上传图片']"),5).click();
       /* // 点击选择文件按钮
        Thread.sleep(3000);
        // 设置等待3秒
        Runtime exe = Runtime.getRuntime();
        // Java 的Runtime 模块的getruntime.exec()方法可以调用exe 程序并执行。
        try {
            String str = "C:/Users/Administrator/Desktop/TestJunit/src/main/java/selenium/add.exe";
            exe.exec(str);
            // 运行指定位置的.exe文件
        } catch (IOException e) {
            System.out.println("Error to run the exe");
            e.printStackTrace();
        }*/
        
        findElement(By.linkText("选择"),3).click();
        findElement(By.xpath("//span[contains(text(),'公告')]"),3).click();
        findElement(By.linkText("确定"),3).click();
        findElement(By.linkText("完成"),3).click();
        return this;
    }

    public ManageTools addLetterAndPicture(String title,String content,String picPath,String coverPagePath,String summary,String author) {
        this.goToSourceLib();
        findElement(By.linkText("图文"),3).click();
        findElement(By.linkText("添加图文"),3).click();
        findElement(By.xpath("//div[@id='js_mpNews_editor_wrap']//input[@placeholder='在此输入标题']"),3).sendKeys(title);
        driver.switchTo().frame(0);
        findElement(By.xpath("//body"),3).sendKeys(content);
        driver.switchTo().defaultContent();
        findElement(By.xpath("//span[contains(text(),'添加附件')]/following::input[1]"),0).sendKeys(picPath);
        findElement(By.linkText("添加封面图"),3).click();
        findElement(By.xpath("//a[contains(text(),'选择图片 ')]/input"),0).sendKeys(coverPagePath);
        findElement(By.linkText("确定"),3).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor)(driver)).executeScript("arguments[0].scrollIntoView(true);", findElement(By.id("js_mpNews_editor_wrap"),3));
        findElement(By.xpath("//input[@placeholder='选填']"),5).sendKeys(author);
        System.out.println(driver.getWindowHandles().toString());
        //此处有坑，下列文本域需要点击div后才可以输入
        findElement(By.xpath("//div[contains(text(),'如不填')]"),3).click();
        findElement(By.xpath("//textarea[@placeholder='如不填会自动抓取正文前54字']"),5).sendKeys(summary);
        findElement(By.linkText("选择"),3).click();
        findElement(By.xpath("//label[@data-name='公告']"),3).click();
        findElement(By.linkText("确定"),3).click();
        findElement(By.linkText("完成"),3).click();


        return this;
    }
}
