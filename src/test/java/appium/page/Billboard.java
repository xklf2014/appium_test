package appium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Billboard extends BasePage{
    //关注风云榜用户
    public Billboard followBillBoard(int rank){
        //通过排名跳转关注用户页面（仅限可见数据）
        List<WebElement> customList = new ArrayList<>();
        customList = findElements(By.id("top_list_item_title"));
        customList.get(rank).click();
        click(By.id("follow_btn"));
        click(By.id("md_buttonDefaultNegative"));

        return this;
    }

    public Billboard followBillBoard(String name){
        //todo 写个递归判断当前元素是否可点击，不可点击滑动页面，递归判断
        //未做退出处理，可能出现死循环
        findCurrentElementFromList(By.xpath("//*[contains(@resource-id,'top_list_item_title') and contains(@text,'"+name+"')]")).click();
        click(By.id("follow_btn"));
        click(By.id("md_buttonDefaultNegative"));

        return this;
    }

    public CombinationPage backToCombinationPage(){
        click(By.id("rl_action_back"));
        return new CombinationPage();

    }
}
