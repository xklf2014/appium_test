package services.api.user.testcase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import services.api.user.api.UserService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestUser {
    static UserService userService = new UserService();
    @Test
    public void getUser(){
        userService.getUser("test111").then().body("name",equalTo("test"));
    }

    @Test
    public void updateUser(){
        String userid = "0987";
        String name = "newName1";
        String address = "Shanghai province YangPu distract Souch Changjiang road no. 245";
        HashMap<String,Object> data = new HashMap<>();
        data.put("name",name);
        data.put("address",address);

        userService.updateUser(userid,data);
        userService.getUser(userid).then().body("name",equalTo(name));
    }

    @Test
    public void createUser(){
        String userName = "story";
        String userId = userName + System.currentTimeMillis();
        String address = "Shanghai province YangPu distract Souch Changjiang road no. 245";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);

        HashMap<String,Object> data = new HashMap<>();
        data.put("department",new int[]{1});
        data.put("name",userName);
        data.put("address",address);
        data.put("mobile",mobile);

        userService.createUser(userId,data).then().body("errcode",equalTo(0));
        userService.getUser(userId).then().body("name",equalTo(userName));

    }

    @Test
    public void cloneUser(){
        String userName = "story1";
        String userId = userName + System.currentTimeMillis();
        String address = "Shanghai province YangPu distract Souch Changjiang road no. 245";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);

        HashMap<String,Object> data = new HashMap<>();
        data.put("department",new int[]{1});
        data.put("name",userName);
        data.put("address",address);
        data.put("mobile",mobile);

        userService.cloneAndCreateUser(userId,data).then().body("errcode",equalTo(0));
        userService.getUser(userId).then().body("name",equalTo(userName));
    }

    @ParameterizedTest
    @CsvSource({
        "abc,abc",
        "ccc,ccc",
        "111,111"

    })
    public void deleteNewUser(String name,String userid){
        String userName = "story2";
        if (userid.isEmpty()) {
            userid = userName + System.currentTimeMillis();
        }
        String address = "Shanghai province YangPu distract Souch Changjiang road no. 245";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);

        HashMap<String,Object> data = new HashMap<>();
        data.put("department",new int[]{1});
        data.put("name",userName);
        data.put("address",address);
        data.put("mobile",mobile);

        userService.cloneAndCreateUser(userid,data).then().body("errcode",equalTo(0));
        userService.deleteUser(userid).then().body("errcode",equalTo(0));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "TestUser.csv")
    public void deleteNewUserFromCsv(String name,String userid){
        if (userid.isEmpty()) {
            userid = name + System.currentTimeMillis();
        }
        String address = "Shanghai province YangPu distract Souch Changjiang road no. 245";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);

        HashMap<String,Object> data = new HashMap<>();
        data.put("department",new int[]{1});
        data.put("name",name);
        data.put("address",address);
        data.put("mobile",mobile);

        userService.cloneAndCreateUser(userid,data).then().body("errcode",equalTo(0));
        userService.deleteUser(userid).then().body("errcode",equalTo(0));
    }

    @ParameterizedTest
    @MethodSource("deleteNewUserFromYamlData")
    public void deleteNewUserFromYaml(String name,String userid){
        if (userid.isEmpty()) {
            userid = name + System.currentTimeMillis();
        }
        String address = "Shanghai province YangPu distract Souch Changjiang road no. 245";
        String mobile = String.valueOf(System.currentTimeMillis()).substring(0,11);

        HashMap<String,Object> data = new HashMap<>();
        data.put("department",new int[]{1});
        data.put("name",name);
        data.put("address",address);
        data.put("mobile",mobile);

        userService.cloneAndCreateUser(userid,data).then().body("errcode",equalTo(0));
        userService.deleteUser(userid).then().body("errcode",equalTo(0));
    }

    static Stream deleteNewUserFromYamlData(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeRef =
                new TypeReference<List<HashMap<String, Object>>>() {};
        List<HashMap<String,Object>> data;
        try {
            data = mapper.readValue(TestUser.class.getResourceAsStream("/services/api/user/testcase/TestUserData.yaml"),typeRef);
            ArrayList<Arguments> resultData = new ArrayList<>();
            data.forEach(map -> {
                resultData.add(arguments(map.get("name"),map.get("userid")));
            });
            return resultData.stream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Stream.of();
    }

    @Test
    public void delete(){
        String userId = "story1578203546207";
        userService.deleteUser(userId).then().body("errcode",equalTo(0));
    }

    @Test
    public void getDepartmentMembers(){
        int departmentId = 1;
        userService.getDepartmentMembers(departmentId).then().body("errcode",equalTo(0));
    }

    @Test
    public void batchDelete(){
        HashMap<String,Object> data = new HashMap<>();
        List<String> userLists = new ArrayList<>();
        userLists.add("asda");
        userLists.add("dsad");
        data.put("useridlist",userLists);

        userService.batchDelete(data).then().body("errcode",equalTo(0));
    }

//    @Test
//    public String template(){
//        HashMap<String, Object> scopes = new HashMap<String, Object>();
//        scopes.put("name", "Mustache");
//        scopes.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
//        scopes.put("userId",System.currentTimeMillis());
//
//
//        Writer writer = new StringWriter();
//        MustacheFactory mf = new DefaultMustacheFactory();
//        Mustache mustache = mf.compile("./services/api/user/testcase/TestUser.yaml");
//        mustache.execute(writer, scopes);
//        try {
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return writer.toString();
//    }


    @ParameterizedTest
    @MethodSource("batchcreateUserByYamlData")
    public void batchcreateUserByYaml(String userName,String userId,String address,String mobile,List<Integer> department){

        HashMap<String,Object> data = new HashMap<>();
        data.put("department",department);
        data.put("name",userName);
        data.put("address",address);
        data.put("mobile",mobile);

        userService.createUser(userId,data).then().body("errcode",equalTo(0));
        userService.getUser(userId).then().body("name",equalTo(userName));

    }

    static Stream batchcreateUserByYamlData(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeRef =
                new TypeReference<List<HashMap<String, Object>>>() {};
        List<HashMap<String,Object>> data;
        try {
            data = mapper.readValue(TestUser.class.getResourceAsStream("/services/api/user/testcase/TestUserDataCreate.yaml"),typeRef);
            ArrayList<Arguments> resultData = new ArrayList<>();
            data.forEach(map -> {
                resultData.add(arguments(map.get("userName"),map.get("userId"),
                        map.get("address"),map.get("mobile"),map.get("department")));
            });
            return resultData.stream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Stream.of();
    }
}
