package appium.model;

import java.util.HashMap;
import java.util.List;

public class PageObjectElement {
    public List<HashMap<String,String>> element;

    public List<HashMap<String, String>> getElement() {
        return element;
    }

    public void setElement(List<HashMap<String, String>> element) {
        this.element = element;
    }
}
