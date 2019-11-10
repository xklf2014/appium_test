package appium.test;

import appium.page.AccountPage;
import appium.page.App;
import appium.page.LoginByPhonePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class TestLoginWithPhone {
    private static AccountPage accountPage;
    private static LoginByPhonePage loginByPhonePage;

    @BeforeAll
    static void setup() throws MalformedURLException {
        App.startUp();
        App.initNavigationBtn();
        accountPage = App.toAccountPage();
    }

    @BeforeEach
    public void before() {
        loginByPhonePage = accountPage.toLoginByPhone();
    }

    @Test
    @ParameterizedTest
    @MethodSource("phoneNumberAndVerifyProvider")
    void login(String phone,String verify){
    //void login(){
        loginByPhonePage.login(phone,verify);
        assertThat(loginByPhonePage.getLoginResultContent(),equalTo("验证码错误"));
    }

    @AfterEach
    void after(){
        loginByPhonePage.backToAccount();
    }

    static Stream<Arguments> phoneNumberAndVerifyProvider() {
        return Stream.of(
                //Arguments.of("13690556001", "1111"),
                Arguments.of("15665333459", "1234")

        );
    }
}
