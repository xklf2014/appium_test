package appium.test;

import appium.page.*;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

public class TestCombination {
    private static StockPage stockPage;
    private static CombinationPage combinationPage;
    private static Billboard billboard;

    @BeforeAll
    static void setup() throws MalformedURLException {
        App.startUp();
        App.initNavigationBtn();
        stockPage = App.toStockPage();

    }

    @BeforeEach
    public void before() {
        combinationPage = stockPage.toCombinationPage();

    }

    @Test
    @Order(1)
    void testFollowBillBoard(){
        billboard = combinationPage.toBillboard();
        //根据当前页面排名查询，不可选不肯见内容
        //billboard.followBillBoard(1);

        billboard.followBillBoard("白酒");
        billboard.backToCombinationPage();
        combinationPage.backToStockPage();

    }

    @Test
    @Order(2)
    void testCancelAllFollows(){
        combinationPage.cancelAllFollow();
    }


}
