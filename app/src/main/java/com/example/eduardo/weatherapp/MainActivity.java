/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.eduardo.weatherapp;

import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import data.JsonWeatherParser;
import data.WeatherHttpClient;
import model.Weather;


public class MainActivity extends ActionBarActivity {
    TextView cityName;
    ImageView icon;
    TextView currentTemp;
    TextView wind;
    TextView cloud;
    TextView pressure;
    TextView humidty;
    TextView sunrise;
    TextView sunset;
    TextView lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherTask weatherTask = new WeatherTask();

        cityName = (TextView) findViewById(R.id.main_city_name);
        icon = (ImageView) findViewById(R.id.main_image);
        currentTemp = (TextView) findViewById(R.id.main_city_temperature);
        wind = (TextView) findViewById(R.id.main_wind);
        cloud = (TextView) findViewById(R.id.main_cloud);
        pressure = (TextView) findViewById(R.id.main_pressure);
        humidty = (TextView) findViewById(R.id.main_humidity);

        sunrise = (TextView) findViewById(R.id.main_rise);
        lastUpdate = (TextView) findViewById(R.id.main_update);

        renderWeatherData("Londrina,BR");
        //icon.setBackground();
        //  sunset=(TextView) findViewById(R.id.main_humidity);


    }

    public void renderWeatherData(String city) {

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric"});

        //hideProgress();

    }

    private class WeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            WeatherHttpClient client = new WeatherHttpClient();
            Weather weather = new Weather();
            String data = client.getWeatherData(params[0]);
            weather = new JsonWeatherParser().getWeatherFromData(data);
            Log.v("DATA", data);

            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            cityName.setText(weather.getPlace().getCity());
            currentTemp.setText("" + weather.getCondition().getCurrentTemp() + " C ");
            //      wind.setText("Speed: " + weather.getWind().getSpeed() + " Degrees: " + weather.getWind().getDegrees());
            cloud.setText("Clouds: " + weather.getCloudPrecipitation());
            pressure.setText("Pressure: " + weather.getCondition().getPressure());
            humidty.setText("Humidity: " + weather.getCondition().getHumidity());
            sunrise.setText("Sunrise: " + weather.getPlace().getSunrise());
            lastUpdate.setText("Last Update: " + weather.getPlace().getLastUpdate());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_city) {
            Dialog dialog = new Dialog(getApplicationContext());
            dialog.setTitle("Mudar Cidade");
            View view = new View(getApplicationContext());

        }

        return super.onOptionsItemSelected(item);
    }
}


