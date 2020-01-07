package services;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

/*
*  接口测试
* */
public class DepartmentServices {
    private static String secret = "ClUhER7s5CXkS8db2DpjAbvy3Jri808i_d5FnKlANZo";
    private static String corpid = "ww575d87ce240d5411";
    private static String token;
    private  int parentDepartId = 9;
    private  String name = "story testing dept";

    @BeforeAll
    public static void testGetToken(){
         token = given()
                .param("corpid",corpid)
                .param("corpsecret",secret)
                .when()
                    .log().all()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                    .log().all()
                    .body("errcode",equalTo(0))
        .extract().body().path("access_token");
        System.out.println(token);

    }

    @Test
    public void addDepartment(){
        //https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN
        HashMap<String,Object> data = new HashMap<>();
        data.put("name",name);
        data.put("parentid",parentDepartId);

        given()
                .queryParam("access_token",token)
                .contentType(ContentType.JSON)
                .body(data)
        .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
        .then()
                .log().all()
                .body("errcode",equalTo(0));
    }

    @Test
    public void getDepartmentList(){
        given()
                .queryParam("access_token",token)
                .queryParam("id",parentDepartId)
        .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
        .then()
                .log().all()
                .body("errcode",equalTo(0));
    }
}
