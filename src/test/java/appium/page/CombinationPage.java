package appium.page;

import org.openqa.selenium.By;

public class CombinationPage extends BasePage{
    //获取当前页面列表数据
    public CombinationPage getCombinationList(){
        //todo 未登录只能关注一位用户
        return this;
    }

    //跳转到组合风云榜页面进行关注
    public Billboard toBillboard(){
        click(By.id("portfolio_empty_import"));
        return new Billboard();
    }

    //取消关注
    public CombinationPage cancelAllFollow(){
        click(By.id("edit_group"));
        click(By.id("check_all"));
        click(By.id("cancel_follow"));
        click(By.id("md_buttonDefaultPositive"));
        click(By.id("action_close"));
        return this;
    }

    public StockPage backToStockPage(){
        click(By.id("top_list_action_bar_back"));
        return new StockPage();
    }
}
