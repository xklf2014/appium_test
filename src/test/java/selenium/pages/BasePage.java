package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public static WebDriver driver;

    public WebElement findElement(By by, int timeout){
        System.out.println(by);
        if(timeout>0) {
            waitClickable(by, timeout);
            System.out.println("clickable");
        }
        return driver.findElement(by);
    }

    public void waitClickable(By by, int timeout){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

   /* public void waitClickable(WebDriver driver, By by){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by));
    }*/

    public void importFromFile(WebDriver driver,String path){

    }

    public  void quit() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }



}
