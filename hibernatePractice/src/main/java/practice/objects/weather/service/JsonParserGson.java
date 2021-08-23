package practice.objects.weather.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParserGson {

    public static <T> T jsonToObject(String json, Class<T> typeOut) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, typeOut);
    }

//    public static String objectToJson(City city){
//        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Date.class, city)
//                .serializeNulls();
//        Gson gson = gsonBuilder.create();
//        return gson.toJson(city);
//    }
}