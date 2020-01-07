package services.api.tag.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.api.tag.api.TagService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestTag {
    static TagService tagService = new TagService();
    String tagName = "story tag";

    @BeforeAll
    public static void beforeAll(){

    }

    @Test
    public void query(){
        tagService.list().then().body("errcode",equalTo(0));
    }

//    @Test
//    public void queryByTagId(){
//        int tagId = tagService.list().then().body("errcode",equalTo(0)).extract()
//                .body().path("taglist.find{tag->tag.tagname=='"+tagName+"'}.tagid");
//        System.out.println(tagId);
//        tagService.list(tagId).then().body("errcode",equalTo(0));
//    }

    @Test
    public void createTag(){
        tagService.createTag(tagName).then().body("errmsg",equalTo("created"));
    }


    @Test
    public void deleteTag(){
        int tagId = tagService.list().then().body("errcode",equalTo(0)).extract()
                .body().path("taglist.find{tag->tag.tagname=='"+tagName+"'}.tagid");
        tagService.deleteTag(tagId).then().body("errmsg",equalTo("deleted"));
    }

    @Test
    public void updateTag(){
        int tagId = tagService.list().then().body("errcode",equalTo(0)).extract()
                .body().path("taglist.find{tag->tag.tagname=='"+tagName+"'}.tagid");
        tagService.updateTag(tagId,"newTag1").then().body("errmsg",equalTo("updated"));
    }

    @Test
    public void addTagUsers(){
        int tagId = 1;
        List<String> userLists = new ArrayList<>();
        userLists.add("TiBoSi");
        List<Integer> partyLists = new ArrayList<>();
        partyLists.add(1);

        HashMap<String,Object> data = new HashMap<>();
        data.put("userlist",userLists);
        data.put("partylist",partyLists);
        tagService.addTagUsers(tagId,data).then().body("errcode",equalTo(0));
    }

    @Test
    public void delTaguUers(){
        int tagId = 1;
        List<String> userLists = new ArrayList<>();
        userLists.add("0987");
        HashMap<String,Object> data = new HashMap<>();
        data.put("userlist",userLists);
        tagService.deleteTagUsers(tagId,data).then().body("errcode",equalTo(0));
    }

    @Test
    public void getTagUsers(){
        tagService.getTagUsers(1).then().body("errmsg",equalTo("ok"));
    }

}
