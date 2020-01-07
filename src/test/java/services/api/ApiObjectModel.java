package services.api;

import io.restassured.response.Response;

import java.util.HashMap;

public class ApiObjectModel {
    public HashMap<String,ApiObjectMethodModel> methods = new HashMap<>();

    public ApiObjectMethodModel getMethod(String method){
        return methods.get(method);
    }

    public Response run(String method){
        return getMethod(method).run();
    }

    public Response run(String method, HashMap<String, Object> params) {
        return getMethod(method).run(params);
    }
}
