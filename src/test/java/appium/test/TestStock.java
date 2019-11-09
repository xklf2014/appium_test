package appium.test;

import appium.page.App;
import appium.page.StockPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import static org.hamcrest.MatcherAssert.*;
import static appium.page.App.initNavigationBtn;
import static appium.page.App.toStockPage;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class TestStock {
    private static StockPage stockPage;
    @BeforeAll
    static void setup() throws MalformedURLException {
        App.startUp();
        App.initNavigationBtn();
    }

    @BeforeEach
    public void before() {
        stockPage = App.toStockPage();
    }

    @Test
    void addSelfChooseStock(){
        if (stockPage.getAllStocks() != null && stockPage.getAllStocks().size() > 0){
                stockPage.deleteAll();
        }

        stockPage.addDefaultStock();
        assertThat(stockPage.getAllStocks().size(),greaterThanOrEqualTo(6));

       /* if (stockPage.getAllStocks().size()>0){
            stockPage.deleteAll();
        }

        stockPage.addDefaultStock();*/
    }
}
