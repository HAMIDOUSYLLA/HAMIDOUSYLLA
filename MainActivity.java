package com.estem.applicationweither;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.color.black;

public class MainActivity<listeEltsVille> extends AppCompatActivity {
    EditText etCity, etCountry;
    TextView tvResult;
    private final String url="http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "9a1efd38ae2ca6a843eab8abd7c26018";
    DecimalFormat df = new DecimalFormat("#.##");
    Helper h= new Helper(MainActivity.this);
    String codePays ;
    String Ville ;
    String temperature;
    String humidite;
    Helper ho = new Helper(MainActivity.this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.etCity);
        etCountry  = findViewById(R.id.etCountry);
        tvResult = findViewById(R.id.tvResult);
    }

    public void getWeatherDetails(View view) {
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if (city.equals("")){
            tvResult.setText("sa doit pas etre nul");
        }else {
            if(!country.equals("")){
                tempUrl = url+"?q="+city+","+country+"&appid="+appid;
            }else {
                tempUrl = url+"?q="+city+"&appid="+appid;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                  //  Log.d("response",response);
                    String output="";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description =  jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp =jsonObjectMain.getDouble("temp") -  273.15;
                        double feelslike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pression = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");
                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");
                        JSONObject jsonObjectSys =  jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonResponse.getString("name");
                        tvResult.setTextColor(Color.WHITE);
                        output += "Ville   "+cityName+" code pays (" + countryName + ")"
                                + "\n Temperature :" + df.format(temp) + " °C"
                               + "\n Feels LIKe :  "+df.format(feelslike)+ "  °C"
                               +"\n Humidity  : " + humidity + "%"
                                + "\n Description: " + description
                                + "\n Vitesse du vent:  " + wind + " m/s"
                                + "\n cloudiness: " + clouds + "%"
                                + "\n pression :" + pression + "hpa";
                        tvResult.setText(output);
                        temperature = df.format(temp);
                        Ville = cityName;
                        codePays = countryName;
                        humidite = humidity+"%";
                        Ville v= new Ville(Ville,temperature);
                        //inserer dans la base de données
                        ho.insertVille(v);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void vaAccueil(View view) {
        Intent i1= new Intent(getApplicationContext(),Accueil.class);
        startActivity(i1);
    }




}