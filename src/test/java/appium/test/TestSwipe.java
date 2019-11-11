package appium.test;

import appium.page.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class TestSwipe {
    @BeforeAll
    static void setup() throws MalformedURLException {
        App.startUp();
    }

    @Test
    void testSwipe() throws InterruptedException {
        Thread.sleep(3000);
        for (int i=0;i<5;i++){
            App.swpie(0.5,0.8,0.5,0.4);
        }
    }
}
