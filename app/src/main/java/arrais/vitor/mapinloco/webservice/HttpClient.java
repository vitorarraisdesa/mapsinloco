package arrais.vitor.mapinloco.webservice;

import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import arrais.vitor.mapinloco.R;
import arrais.vitor.mapinloco.entity.Location;

/**
 * Created by Vitor on 23/07/2016.
 */
public class HttpClient {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/find?lat={LAT}&lon={LON}&cnt=15&APPID=<APP_ID>";
    private static String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city name}&APPID=<APP_ID>";
    private String WEATHER_API = "638d7e359353824821fd19d5bc9530ab";
    Location loc = new Location();

    public String getWeatherData(String latitude, String longitude) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(replaceURL(latitude, longitude))).openConnection();
            Log.d("URL", replaceURL(latitude, longitude));
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    public String getCityWeather(String cityName) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(replaceCityURL(cityName))).openConnection();
            Log.d("URL", replaceCityURL(cityName));
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    private String replaceURL (String latitude, String longitude){
        String newString = BASE_URL.replace("{LAT}", latitude);
        newString = newString.replace("{LON}", longitude);
        newString = newString.replace("<APP_ID>", WEATHER_API);

        return newString;
    }

    private String replaceCityURL (String city){
        String newString = WEATHER_URL.replace("{city name}", city);
        newString = newString.replace("<APP_ID>", WEATHER_API);

        return newString;
    }

}
