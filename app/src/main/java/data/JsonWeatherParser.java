package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Condition;
import model.Place;
import model.Weather;
import model.Wind;
import util.Utils;

/**
 * Created by eduardo on 18/08/2015.
 */
public class JsonWeatherParser {

    public Weather getWeatherFromData(String data){
        Weather weather=new Weather();
        try {
            JSONObject jsonObject=new JSONObject(data);
            Place place=new Place();
            Condition condition=new Condition();
            JSONObject obj= Utils.getObject("coord", jsonObject);
            place.setLat(obj.getDouble("lat"));
            place.setLon(obj.getDouble("lon"));
            JSONArray jsonArray=jsonObject.getJSONArray("weather");
            obj=jsonArray.getJSONObject(0);
            condition.setIcon(obj.getString("icon"));
            condition.setCondition(obj.getString("main"));
            condition.setDescription(obj.getString("description"));
            obj=jsonObject.getJSONObject("main");
            condition.setCurrentTemp(obj.getDouble("temp"));
            condition.setPressure(obj.getDouble("pressure"));
            condition.setHumidity(obj.getDouble("humidity"));
            condition.setMinTemp(obj.getDouble("temp_min"));
            condition.setMaxTemp(obj.getDouble("temp_max"));
            weather.setCloudPrecipitation(jsonObject.getJSONObject("clouds").getDouble("all"));
            obj=jsonObject.getJSONObject("sys");
            place.setCountry(obj.getString("country"));
            place.setSunrise(obj.getLong("sunrise"));
            place.setSunset(obj.getLong("sunset"));
            place.setCity(jsonObject.getString("name"));
            obj=jsonObject.getJSONObject("wind");
            Wind wind=new Wind();
            wind.setDegrees(obj.getDouble("deg"));
            wind.setSpeed(obj.getDouble("speed"));
            place.setLastUpdate(jsonObject.getLong("dt"));
            weather.setCondition(condition);
            weather.setPlace(place);
            weather.setWind(wind);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return weather;
    }
}
