package appium.unit;

import appium.model.PageObectModel;
import appium.model.PageObjectElement;
import appium.model.PageObjectMethod;
import appium.page.BasePage;
import appium.utils.DataDriverProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataDriverProvider {

    @Test
    public void testDataProvider() throws JsonProcessingException {
        HashMap<String, DataDriverProvider> dataProvider = new HashMap<String, DataDriverProvider>();
        DataDriverProvider dataDriverProvider = new DataDriverProvider();
        List<HashMap<String ,String >> list = new ArrayList<HashMap<String ,String >>();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id","search");
        hashMap.put("xpath","//*[@text='aaa']");
        list.add(hashMap);
        list.add(hashMap);

        dataDriverProvider.setDataProvider(list);
        dataProvider.put("search",dataDriverProvider);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataProvider));


    }

    @Test
    public void testModel() throws JsonProcessingException {
        PageObectModel pageObectModel = new PageObectModel();
        PageObjectElement element = new PageObjectElement();
        PageObjectMethod method = new PageObjectMethod();

        List<HashMap<String,String>> list2 = new ArrayList<>();
        HashMap<String,String> map2 = new HashMap<>();
        map2.put("id","aaaa");
        map2.put("xpath","bbbb");

        list2.add(map2);
        list2.add(map2);
        element.setElement(list2);
        System.out.println(list2);
        pageObectModel.elements.put("locator",element);




        List<HashMap<String,String>> list = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        map.put("id","aaaa");
        map.put("name","bbbb");
        list.add(map);
        list.add(map);
        method.setDataProvider(list);
        pageObectModel.methods.put("search",method);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pageObectModel));
    }

    @Test
    public void testModel2() throws JsonProcessingException {
        PageObjectElement element = new PageObjectElement();
        List<HashMap<String,String>> list2 = new ArrayList<>();
        HashMap<String,String> map2 = new HashMap<>();
        map2.put("id","aaaa");
        map2.put("xpath","bbbb");

        list2.add(map2);
        list2.add(map2);
        element.setElement(list2);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(element));
    }




}
