package homeWork.jsonParserPractice;

import homeWork.jsonParserPractice.model.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

class JsonParserGson {

    static City jsonToObject(String fileName){
        City city = new City();
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Date.class, city);
        Gson gson = gsonBuilder.create();
        try (FileReader fileReader = new FileReader(fileName)){
            city = gson.fromJson(fileReader, City.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

    static String objectToJson(City city){
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Date.class, city)
                .serializeNulls();
        Gson gson = gsonBuilder.create();
        return gson.toJson(city);
    }
}
