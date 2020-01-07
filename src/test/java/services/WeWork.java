package services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WeWork {
    private static WeWork weWork;
    String token;
    private String secret = "ClUhER7s5CXkS8db2DpjAbvy3Jri808i_d5FnKlANZo";
    private String corpid = "ww575d87ce240d5411";

    public static WeWork getInstance(){
        if(weWork == null){
            weWork = new WeWork();
        }
        return weWork;
    }

    public  String getToken(){
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
        return token;
    }
}
