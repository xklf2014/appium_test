package services.api.tag.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import services.WeWork;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TagService {

    public Response list(){
        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/tag/list")
                .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response list(int tagId){
        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .queryParam("tagid",tagId)
                .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/tag/get")
                .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response createTag(String name){
        HashMap<String,Object> data = new HashMap<>();
        data.put("tagname",name);

        return given()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/create")
                .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }


    public Response deleteTag(int tagId){
        return given()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .queryParam("tagid",tagId)
                .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/tag/delete")
                .then()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response updateTag(int tagId,String tagName){
        HashMap<String,Object> data = new HashMap<>();
        data.put("tagid",tagId);
        data.put("tagname",tagName);

        return given()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/update")
                .then()
                .body("errcode",equalTo(0))
                .extract().response();
    }


    public Response getTagUsers(int tagId){
        return given()
                .queryParam("access_token", WeWork.getInstance().getToken())
                .queryParam("tagid",tagId)
                .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/tag/get")
                .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response addTagUsers(int tagId,HashMap<String,Object> datas){
        List userlist = (List) datas.get("userlist");
        List partylist = (List) datas.get("partylist");
        HashMap<String,Object> data = new HashMap<>();
        data.put("tagid",tagId);
        if ( userlist != null || partylist != null){
            if (userlist!=null && userlist.size() > 0) {
                data.put("userlist", userlist);
            }
            if (partylist != null && partylist.size() > 0) {
                data.put("partylist", partylist);
            }
        }else{
            return null;
        }

        return given()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers")
                .then()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response deleteTagUsers(int tagId,HashMap<String,Object> datas){
        List userlist = (List) datas.get("userlist");
        List partylist = (List) datas.get("partylist");
        HashMap<String,Object> data = new HashMap<>();
        data.put("tagid",tagId);
        if ( userlist != null || partylist != null){
            if (userlist!=null && userlist.size() > 0) {
                data.put("userlist", userlist);
            }
            if (partylist != null && partylist.size() > 0) {
                data.put("partylist", partylist);
            }
        }else{
            return null;
        }

        return given()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers")
                .then()
                .body("errcode",equalTo(0))
                .extract().response();
    }

}
