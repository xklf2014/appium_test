package services.api.user.testcase;

import org.junit.jupiter.api.Test;
import services.api.user.api.UserServiceApi;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestUserApi {
    @Test
    public void get(){
        UserServiceApi userServiceApi = new UserServiceApi();
        userServiceApi.getUser("story11578206671544").then().body("errcode",equalTo(0));
    }

    @Test
    public void delete(){
        UserServiceApi userServiceApi = new UserServiceApi();
        userServiceApi.deleteUser("1111").then().body("errcode",equalTo(0));
    }

}
