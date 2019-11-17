package appium.test;

import appium.page.App;
import appium.page.SearchPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
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
    public static Collection<Object[]> data() throws IOException {
       /* return Arrays.asList(new Object[][]{
                {"alibaba",100f},
                {"xiaomi",5f},
                {"jd",10f},
                {"wankea",20f}
        });*/
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object[][] objects = mapper.readValue(TestSearch.class.getResourceAsStream("/app/Stock.yaml"),Object[][].class);
        return Arrays.asList(objects);
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public Double price;


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
        assertThat(searchPage.search(stock).getCurrentPrice(), greaterThanOrEqualTo(price.floatValue()));
    }
}
