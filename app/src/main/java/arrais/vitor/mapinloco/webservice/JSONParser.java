package arrais.vitor.mapinloco.webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import arrais.vitor.mapinloco.entity.City;
import arrais.vitor.mapinloco.entity.Location;
import arrais.vitor.mapinloco.entity.Weather;

/**
 * Created by Vitor on 23/07/2016.
 */
public class JSONParser {
    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        JSONObject jObj = new JSONObject(data);

        Location loc = new Location();

        JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getDouble("lat", coordObj));
        loc.setLongitude(getDouble("lon", coordObj));

        weather.location = loc;

        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("weather");

        // We use only the first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
        weather.currentCondition.setDescr(getString("description", JSONWeather));
        weather.currentCondition.setCondition(getString("main", JSONWeather));

        JSONObject mainObj = getObject("main", jObj);
        weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
        weather.temperature.setMinTemp(getFloat("temp_min", mainObj));
        weather.temperature.setTemp(getFloat("temp", mainObj));


        return weather;
    }

    public static City getCities(String data) throws JSONException {
        City city = new City();
        JSONObject jObj = new JSONObject(data);

        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject JSONWeather = jArr.getJSONObject(1);
        city.setName(getString("name", JSONWeather));

        return city;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static double  getDouble(String tagName, JSONObject jObj) throws JSONException {
        return  jObj.getDouble(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float)  jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}
