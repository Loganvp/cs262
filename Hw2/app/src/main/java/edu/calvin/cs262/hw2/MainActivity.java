package edu.calvin.cs262.hw2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Reads openweathermap's RESTful API for weather forecasts.
 * The code is based on Deitel's WeatherViewer (Chapter 17), simplified based on Murach's NewsReader (Chapter 10).
 *
 * for CS 262, lab 6
 * This has been edited for homework 2
 *
 * @author kvlinden
 * @version summer, 2016\
 *
 * @author Loganvp
 * @version Fall, 2016
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private EditText cityText;
    private Button fetchButton;

    private List<Player> playerList = new ArrayList<>();
    private ListView itemsListView;

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityText = (EditText) findViewById(R.id.cityText);
        fetchButton = (Button) findViewById(R.id.fetchButton);
        itemsListView = (ListView) findViewById(R.id.weatherListView);

        // Reset TextEdit field to blank and apps title to homework 2
        cityText.setText("");
        setTitle("Monopoly Players for Lab9");

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissKeyboard(cityText);
                new GetWeatherTask().execute(createURL(cityText.getText().toString()));
            }
        });
    }

    /**
     * Formats a URL for the webservice specified in the string resources.
     *
     * @param id the target player if
     * @return URL formatted for cs262.cs.calvin.edu:8089
     */
    private URL createURL(String id) {
        //Log.d("id", id);
        //boolean idIs1 = (Integer.parseInt(id) == 1);
        //Log.d("id is 1", String.valueOf(idIs1));
        try {
            if (Integer.parseInt(id) == 1 || Integer.parseInt(id) == 2 || Integer.parseInt(id) == 3) {
                try {
                    String urlString = "http://cs262.cs.calvin.edu:8089/monopoly/player/" +
                            URLEncoder.encode(id, "UTF-8");
                    Log.d("API key", urlString);
                    return new URL(urlString);
                } catch (Exception e) {
                    Toast.makeText(this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input is not a number", Toast.LENGTH_SHORT).show();
        }
        if (id.equals("")) {
            try {
                String urlString = "http://cs262.cs.calvin.edu:8089/monopoly/players/";
                Log.d("API key", urlString);
                return new URL(urlString);
            } catch (Exception e) {
                Toast.makeText(this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        }

        return null;
    }

    /**
     * Deitel's method for programmatically dismissing the keyboard.
     *
     * @param view the TextView currently being edited
     */
    private void dismissKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Inner class for GETing the current player data from cs262.cs.calvin.edu:8089 asynchronously
     */
    private class GetWeatherTask extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... params) {
            HttpURLConnection connection = null;
            StringBuilder result = new StringBuilder();
            try {
                connection = (HttpURLConnection) params[0].openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    Log.d("json", result.toString());
                    //return new JSONObject(result.toString().substring(1, result.toString().length() - 1));
                    Log.d("single", result.toString().substring(0, 1));
                    Log.d("singleBool", String.valueOf(result.toString().substring(0, 1) == "{"));
                    if (result.charAt(0) == '{'){
                        Log.d("completed json", "{ \"players\" : [" + result.toString() + "]}");
                        return new JSONObject("{ \"players\" : [" + result.toString() + "]}");
                    }
                    Log.d("completed json", "{ \"players\" : " + result.toString() + "}");
                    return new JSONObject("{ \"players\" :" + result.toString() + "}");
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject weather) {
            if (weather != null) {
                //Log.d(TAG, weather.toString());
                convertJSONtoArrayList(weather);
                MainActivity.this.updateDisplay();
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Converts the JSON player data to an arraylist suitable for a listview adapter
     *
     * @param forecast, a list of players
     */
    private void convertJSONtoArrayList(JSONObject forecast) {
        playerList.clear(); // clear old weather data
        try {
            JSONArray list = forecast.getJSONArray("players");
            Log.d(list.toString(), "Json text");
            Log.d("text", " text");
            for (int i = 0; i < list.length(); i++) {
                JSONObject player = list.getJSONObject(i);
                //JSONObject id = player.getJSONObject("id");
                //JSONObject emailAddress = player.getJSONArray("emailaddress").getJSONObject(0);
                try {
                    playerList.add(new Player(
                            player.getInt("id"),
                            player.getString("emailaddress"),
                            player.getString("name")));
                } catch (org.json.JSONException e) {
                    playerList.add(new Player(
                            player.getInt("id"),
                            player.getString("emailaddress"),
                            "no name"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refresh the player data on the player list ListView through a simple adapter
     */
    private void updateDisplay() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        if (playerList == null) {
            Toast.makeText(MainActivity.this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
        }
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        String responce = playerList.toString().substring(1, playerList.toString().length() - 1);
        Log.d(responce, "weather json");
        for (Player item : playerList) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", numberFormat.format(item.getID()));
            map.put("emailaddress", item.getEmailAddress());
            map.put("name", item.getName());
            data.add(map);
        }

        int resource = R.layout.player_item;
        String[] from = {"id", "name", "emailaddress"};
        int[] to = {R.id.dayTextView, R.id.summaryTextView, R.id.emailTextView};

        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);
    }

}
