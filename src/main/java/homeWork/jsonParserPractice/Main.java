package homeWork.jsonParserPractice;

import homeWork.jsonParserPractice.model.City;
import homeWork.jsonParserPractice.model.Industry;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        City cityGson = JsonParserGson.jsonToObject("test.json");
        //System.out.println(JsonParserGson.objectToJson(cityGson));
        System.out.println(JsonParserSimple.jsonToObject("test.json"));
        System.out.println(JsonParserSimple.objectToJson(cityGson));

        List<Industry> industryList = new ArrayList<>();
        industryList.add(new Industry(1, "Medicine"));
        industryList.add(new Industry(2, "Cosmic"));

        City city = new City(1, "Novosibirsk", 900000, "Novosibirskaya oblast",
                industryList, new Date());
        //System.out.println(JsonParserGson.objectToJson(city));
        //System.out.println(buildJson());

    }

    private static String buildJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 361);
        jsonObject.addProperty("name", "Moscow");
        jsonObject.addProperty("citizens", 2300000);
        jsonObject.addProperty("region", "Moskovskaya oblast");
        jsonObject.addProperty("date", "14.03.2020");

        JsonArray jsonArray = new JsonArray();
        JsonObject industry1 = new JsonObject();
        industry1.addProperty("id", 4);
        industry1.addProperty("name", "null");
        JsonObject industry2 = new JsonObject();
        industry2.addProperty("id", 5);
        industry2.addProperty("name", "Construction");
        jsonArray.add(industry1);
        jsonArray.add(industry2);

        jsonObject.add("industryList", jsonArray);

        // create the gson using the GsonBuilder. Set pretty printing on. Allow
        // serializing null and set all fields to the Upper Camel Case
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        return gson.toJson(jsonObject);
    }
}
