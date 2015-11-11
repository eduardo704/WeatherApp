package data;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import util.Utils;

/**
 * Created by eduardo on 18/08/2015.
 */
public class WeatherHttpClient {

    public String getWeatherData(String place){
        HttpURLConnection connection;
        StringBuffer buffer=new StringBuffer();
        StringBuilder responseStrBuilder = new StringBuilder();

        try {
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL + place)).openConnection();


            connection.setDoInput(true); //Sets the flag indicating whether this {@code URLConnection} allows input
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");

            connection.connect();
            int response = connection.getResponseCode();
            Log.d("TESTE", "The response is: " + response);

            InputStream inputStream=connection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
           // StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = bufferedReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }


            inputStream.close();
            connection.disconnect();



        } catch (IOException e) {
            e.printStackTrace();
            Log.v("ERRRO QUE DEU", e.getMessage());
        }
        return responseStrBuilder.toString();
    }


}
