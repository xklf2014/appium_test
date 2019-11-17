package appium.utils;

import appium.test.TestSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TestResource {

    public String name;
    public int age;

    @Test
    public void readFile() throws IOException {
        System.out.println(this.getClass().getResource("/"));

        System.out.println(this.getClass().getResource("/app/Stock.yaml"));
        System.out.println(FileUtils.readFileToString(new File(this.getClass().getResource("/app/Stock.yaml").getPath())));

    }

    @Test
    public void readJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        /*TestResource value = mapper.readValue(new File("/app/MyValue.json"),this.getClass());
        System.out.println(value);
        System.out.println(value.name);*/
        //mapper.writeValue(new File("demo.json"),this);
        TestResource value = mapper.readValue(new File("demo.json"), this.getClass());
        System.out.println(value.name);

    }

    @Test
    public void readYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object[][] objects = mapper.readValue(this.getClass().getResourceAsStream("/app/Stock.yaml"),Object[][].class);
        System.out.println(objects);
        System.out.println(objects[0][0]);
    }


    @Test
    public void testReadYaml(Class t){
        //TestSearch t = new TestSearch();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println("/"+t.getClass().getCanonicalName().replaceAll("\\.","/").concat(".yaml"));
        //mapper.readValue(c.getClass().getResourceAsStream(c.getCanonicalName()));

    }


}


