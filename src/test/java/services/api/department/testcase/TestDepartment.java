package services.api.department.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.api.department.api.DepartmentService;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TestDepartment {
    static DepartmentService departmentService = new DepartmentService();

    @BeforeAll
    public static void beforeAll(){
        ArrayList<Integer> ids = departmentService.list(departmentService.getParentDepartId()).then().extract().body()
                .path("department.findAll{dept->dept.parentid=="+departmentService.getParentDepartId()+"}.id");
        System.out.println(ids);
        ids.forEach(id-> departmentService.delete(id));
    }

    @Test
    public void query(){
        departmentService.list(departmentService.getParentDepartId()).then().body("errmsg",equalTo("ok"));
    }

    @Test
    public void createDepartment(){
        String name = "story";
        departmentService.createDepartment(name).then().body("errmsg",equalTo("created"));
        departmentService.list(departmentService.getParentDepartId()).then()
                .body("department.findAll{dept->dept.name=='"+name+"'}.id",hasSize(1));
    }

    @Test
    public void deleteDepartment(){
        int id = departmentService.createDepartment("test_delete").then().body("errmsg",equalTo("created")).extract().body().path("id");
        departmentService.delete(id).then().body("errmsg",equalTo("deleted"));
    }

    @Test
    public void updateDepartment(){

    }

}
