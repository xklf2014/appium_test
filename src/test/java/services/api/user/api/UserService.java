package services.api.user.api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.response.Response;
import services.WeWork;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserService{

    public Response getUser(String userId) {
        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .queryParam("userid",userId)
        .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
        .then()
                .log().all()
                .extract().response();
    }

    public Response updateUser(String userId, HashMap<String, Object> data) {
        data.put("userid",userId);


        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .body(data)
        .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
        .then().log().all().extract().response();

    }

    public Response deleteUser(String userId) {
        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .queryParam("userid",userId)
                .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then()
                .log().all()
                .extract().response();
    }

    public Response createUser(String userId, HashMap<String, Object> data) {
        data.put("userid",userId);


        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .body(data)
                .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();

    }

    public Response cloneAndCreateUser(String userId,HashMap<String,Object> data){
        data.put("userid",userId);

        String body = cloneFromTemplata("./services/api/user/testcase/TestUser.yaml",data);
        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .body(body)
        .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
        .then()
                .log().all()
                .extract().response();

    }

    public static String cloneFromTemplata(String templatePath,HashMap<String, Object> data) {
//        HashMap<String, Object> scopes = new HashMap<String, Object>();
//        scopes.put("name", "Mustache");
//        scopes.put("mobile", String.valueOf(System.currentTimeMillis()).substring(0, 11));
//        scopes.put("userId", System.currentTimeMillis());


        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templatePath);
        mustache.execute(writer, data);
        try {
            writer.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public Response getDepartmentMembers(int department){
        return given()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .queryParam("department_id",department)
            .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
            .then()
                .log().all()
                .extract().response();
    }

    public Response batchDelete(HashMap<String,Object> data){
        return given()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .body(data)
            .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete")
            .then()
                .log().all()
                .extract().response();
    }
}
