package appium.test;

import appium.page.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestFollowTopUsers {

    private static FollowPage followPage;
    private static FollowUserPage followUserPage;

    @BeforeAll
    static void setup() throws MalformedURLException {
        App.startUp();
        App.initNavigationBtn();
        followPage = App.toFollowPage();
    }

    @BeforeEach
    public void before() {
        followUserPage = followPage.toFollowUserPage();
    }

    @AfterEach
    void after(){
        followUserPage.backToFollowPage();
    }

    @Test
    void testFollowTopUsers(){
        //关注前几位用户
        followUserPage.followTopUsers(3);
        assertThat(followUserPage.findFollowedUsers(),equalTo(3));
    }


}
