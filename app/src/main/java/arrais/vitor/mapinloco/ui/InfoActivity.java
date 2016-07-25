package arrais.vitor.mapinloco.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import arrais.vitor.mapinloco.R;

/**
 * Created by Vitor on 23/07/2016.
 */
public class InfoActivity extends Activity{

    private TextView maxTemperature, minTemperature, descriptionWeather;
    float maxTemp, minTemp;
    String description = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_information);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            maxTemp = extras.getFloat("max temp");
            minTemp = extras.getFloat("min temp");
            description = extras.getString("desc");

            Log.d("Info", maxTemp + " " + minTemp + " " + description);

            maxTemp = maxTemp - (float)273.15;
            minTemp = minTemp - (float)273.15;

            maxTemperature = (TextView) findViewById(R.id.max_temp);
            minTemperature = (TextView) findViewById(R.id.min_temp);
            descriptionWeather = (TextView) findViewById(R.id.climate_description);

            maxTemperature.setText("Max Temperature: "+ String.valueOf(maxTemp));
            minTemperature.setText("Min Temperature: "+ String.valueOf(minTemp));
            descriptionWeather.setText("Climate Description: "+ description);
        }
    }
}
