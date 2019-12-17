package appium.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDriverProvider {
    public List<HashMap<String,String>> dataProvider = new ArrayList<>();

    public List<HashMap<String, String>> getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(List<HashMap<String, String>> dataProvider) {
        this.dataProvider = dataProvider;
    }
}
