package arrais.vitor.mapinloco.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import arrais.vitor.mapinloco.entity.Weather;
import arrais.vitor.mapinloco.webservice.HttpClient;
import arrais.vitor.mapinloco.webservice.JSONParser;

/**
 * Created by Vitor on 23/07/2016.
 */
public class ListViewActivity extends ListActivity {

    private String[] citiesArray;
    private double latitude, longitude;
    String cities;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            this.cities = extras.getString("Cities Names");
            latitude = extras.getDouble("Latitude");
            longitude = extras.getDouble("Longitude");
            citiesArray = new String[] {cities};
        }

        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, citiesArray));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{cities});

    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new HttpClient()).getCityWeather(cities));

            try {
                weather = JSONParser.getWeather(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            Intent intent = new Intent (ListViewActivity.this, InfoActivity.class);
            intent.putExtra("max temp", weather.temperature.getMaxTemp());
            intent.putExtra("min temp", weather.temperature.getMinTemp());
            intent.putExtra("desc", weather.currentCondition.getDescr());
            startActivity(intent);

        }
    }
}
