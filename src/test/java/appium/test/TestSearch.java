package appium.test;

import appium.page.App;
import appium.page.SearchPage;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@RunWith(Parameterized.class)
public class TestSearch {
    public static SearchPage searchPage;

    @BeforeClass
    public static void beforeAll() throws MalformedURLException {
        App.startUp();
    }



    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"alibaba",100f},
                {"xiaomi",5f},
                {"jd",10f},
                {"wankea",20f}
        });
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public Float price;


    @Before
    public  void beforeMethod(){
        searchPage = App.toSearch();
    }

    @After
    public void after(){
        searchPage.cancel();
    }

    @Test
    public void search(){
        assertThat(searchPage.search(stock).getCurrentPrice(), greaterThanOrEqualTo(price));
    }
}
