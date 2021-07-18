package restaurant;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataBase {
    private Map<String, ArrayList> map;

    public DataBase() {
        map = new HashMap<>();
        map.put("customerDataBase", new ArrayList());
        map.put("orderDataBase", new ArrayList());
        map.put("dishDataBase", new ArrayList());
    }

    public Map<String, ArrayList> getMap() {
        return map;
    }

    public void setMap(Map<String, ArrayList> map) {
        this.map = map;
    }
}
