package appium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Billboard extends BasePage{
    //关注风云榜用户
    public Billboard followBillBoard(int rank){
        List<WebElement> customList = new ArrayList<>();
        customList = findElements(By.id("top_list_item_title"));
        //todo 写个递归判断当前元素是否可点击，不可点击滑动页面，递归判断
        customList.get(rank).click();

        click(By.id("follow_btn"));
        click(By.id("md_buttonDefaultNegative"));

        return this;
    }

    public CombinationPage backToCombinationPage(){
        click(By.id("rl_action_back"));
        return new CombinationPage();

    }
}
