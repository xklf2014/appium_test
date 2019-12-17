package appium.test;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidMobileCommandHelper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestFinger {
    AndroidDriver driver;


    @Test
    public void testFinger() throws MalformedURLException, InterruptedException {

        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setCapability("automationName","UiAutomator2");
        cap.setCapability("deviceName", "aitou");
        cap.setCapability("platformName", "Android");
        cap.setCapability("appPackage","com.aitou.web");
        cap.setCapability("appActivity","com.apprn_rebuild.MainActivity");
        //cap.setCapability("appPackage","com.xueqiu.android");
        //cap.setCapability("appActivity",".view.WelcomeActivityAlias");

        //cap.setCapability("unicodeKeyboard", "True");
        //cap.setCapability("resetKeyboard", "True");
        cap.setCapability("noReset",true);
        cap.setCapability("autoGrantPermissions",true);
        cap.setCapability("unlockType","pattern");
        cap.setCapability("unlockKey","3698");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //System.out.println("sleep 10 before");
        //Thread.sleep(5000);
        //System.out.println("sleep 10 after");
        //driver.lockDevice();
       /* boolean isLocked = driver.isDeviceLocked();
        if (isLocked){
            driver.unlockDevice();

        }*/
        //driver.unlockDevice();
        //driver.fingerPrint(1);

       /* Process process = null;

        String osName = System.getProperty("os.name");

                String command = "adb shell input keyevent 224";   //sdk所在位置

        try

        {

            process=Runtime.getRuntime().exec(command);

            InputStreamReader ir  =new InputStreamReader(process.getInputStream());

            LineNumberReader input= new LineNumberReader(ir);

            String line;

            while((line=input.readLine())!=null)

                System.out.println(line);

        }

        catch(IOException e){

            System.err.println("IOException"+e.getMessage());

        }


        System.out.println(osName+"/n");*/







    }


   /* desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "appium");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity",".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("autoGrantPermissions",true);*/
}



