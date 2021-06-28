package homeWork.jsonParserPractice.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class City implements JsonDeserializer<Date>, JsonSerializer<Date>{
    private int id;
    private String name;
    private int citizens;
    private String region;
    private List<Industry> industryList;
    private Date date;

    public City(int id, String name, int citizens, String region, List<Industry> industryList, Date date){
        this.id = id;
        this.name = name;
        this.citizens = citizens;
        this.region = region;
        this.industryList = industryList;
        this.date = date;
    }

    public City(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCitizens() {
        return citizens;
    }

    public void setCitizens(int citizens) {
        this.citizens = citizens;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Industry> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<Industry> industryList) {
        this.industryList = industryList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", citizens=" + citizens +
                ", region='" + region + '\'' +
                ", industryList=" + industryList +
                ", date=" + date +
                '}';
    }

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String date = jsonElement.getAsString();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            System.err.println("Failed to parse Date due to:" + e.getMessage());
            return null;
        }
    }

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return new JsonPrimitive(dateFormat.format(date));
    }
}
