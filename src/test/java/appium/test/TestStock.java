package appium.test;

import appium.model.PageObjectElement;
import appium.page.App;
import appium.page.BasePage;
import appium.page.StockPage;
import appium.unit.TestDataDriverProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class TestStock {
    private static StockPage stockPage;
    @BeforeAll
    @Ignore
    static void setup() throws MalformedURLException {
        //App.getAppInstance().startUp();
        //App.initNavigationBtn();
    }

    @BeforeEach
    public void before() {
        //stockPage = App.getAppInstance().toStockPage();
    }



    @Test
    @Ignore
    void addSelfChooseStock(){
      /*  if (stockPage.getAllStocks() != null && stockPage.getAllStocks().size() > 0){
                stockPage.deleteAll();
        }

        stockPage.addDefaultStock();
        assertThat(stockPage.getAllStocks().size(),greaterThanOrEqualTo(6));*/

       /* if (stockPage.getAllStocks().size()>0){
            stockPage.deleteAll();
        }

        stockPage.addDefaultStock();*/
    }

    @Test
    public void testDataProviders(){
        //System.out.println(stock+"======="+price);
        PageObjectElement element  = new BasePage().dataProvider(this.getClass());

    }

}
