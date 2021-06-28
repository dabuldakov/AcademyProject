package homeWork.jsonParserPractice;

import homeWork.jsonParserPractice.model.City;
import homeWork.jsonParserPractice.model.Industry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonParserSimple {

    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    static City jsonToObject(String fileName){

        JSONParser parser = new JSONParser();
        City city = new City();
        List<Industry> industryList = new ArrayList<>();
        city.setIndustryList(industryList);

        try(FileReader fr = new FileReader(fileName)) {
            JSONObject jsonObject = (JSONObject) parser.parse(fr);
            long id = (Long) jsonObject.get("id");
            long citizens = (Long) jsonObject.get("citizens");
            String name = (String) jsonObject.get("name");
            String region = (String) jsonObject.get("region");
            String date = (String) jsonObject.get("date");
            Date dateObject = null;
            try {
                dateObject = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            city.setId((int) id);
            city.setName(name);
            city.setCitizens((int) citizens);
            city.setRegion(region);
            city.setDate(dateObject);

            JSONArray jsonArray = (JSONArray) jsonObject.get("industryList");

            for (Object o : jsonArray) {
                JSONObject jsonObject1 = (JSONObject) o;
                long id1 = (Long) jsonObject1.get("id");
                String name1 = (String) jsonObject1.get("name");
                Industry industry = new Industry((int) id1, name1);
                industryList.add(industry);
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return city;
    }

    static String objectToJson(City city){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", city.getId());
            jsonObject.put("name", city.getName());
            jsonObject.put("citizens", city.getCitizens());
            jsonObject.put("region", city.getRegion());
            jsonObject.put("date", dateFormat.format(city.getDate()));
            JSONArray jsonArray = new JSONArray();
            for (Industry ind : city.getIndustryList()){
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("id", ind.getId());
                jsonObject1.put("name", ind.getName());
                jsonArray.add(jsonObject1);
            }
            jsonObject.put("industryList", jsonArray);
        return jsonObject.toJSONString();
    }
}
