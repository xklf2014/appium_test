package services.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import services.WeWork;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiObjectMethodModel {
    public HashMap<String,Object> params;
    public HashMap<String,Object> query;
    public HashMap<String,Object> header;
    public HashMap<String,Object> body;
    public String bodyRaw;
    public String method="get";
    public String url;

    public Response run(){
        RequestSpecification request = given();
        request.param("access_token", WeWork.getInstance().getToken());

        if (query != null){
            query.entrySet().forEach(entry->{
                request.queryParam(entry.getKey(),replace(entry.getValue().toString()));
            });
        }

        if (header != null){
            header.entrySet().forEach(entry->{
                request.header(entry.getKey(),replace(entry.getValue().toString()));
            });
        }

        if (body != null){
            request.body(body);
        }

        if (bodyRaw != null){
            request.body(replace(bodyRaw));
        }

        return request.when().log().all().request(method,url)
                .then().log().all().extract().response();

    }

    public String replace(String raw){
        for (Map.Entry<String,Object> kv : params.entrySet()){
            String matcher = "${" +kv.getKey() +"}";
            if (raw.contains(matcher)){
                System.out.println(kv);
                raw = raw.replace(matcher,kv.getValue().toString());
            }
        }
        return raw;
    }

    public Response run(HashMap<String, Object> params) {
        this.params = params;
        return run();
    }
}
