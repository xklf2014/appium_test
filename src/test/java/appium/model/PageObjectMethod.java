package appium.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageObjectMethod {
    public List<HashMap<String,String>> dataProvider;

    public List<HashMap<String, String>> getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(List<HashMap<String, String>> dataProvider) {
        this.dataProvider = dataProvider;
    }
}
