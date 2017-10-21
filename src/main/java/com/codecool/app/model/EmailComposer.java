package com.codecool.app.model;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class EmailComposer {

    public static String apiRequestHandler(String address) throws IOException {
        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?origin=");
        String adrEncoded = "";
        try {
            adrEncoded = URLEncoder.encode(address,"UTF-8");

        } catch (UnsupportedEncodingException e) {e.printStackTrace();}

        sb.append(adrEncoded);
        sb.append("&destination=Budapest,+Bathory+u.+81,+1196&sensor=false");
        String requestUri = sb.toString();

//        Connect to the URL using java's native library
        URL url = new URL(requestUri);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

//        parsing and traveling to the duration part of the json
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(
                new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray routes = rootObj.get("routes").getAsJsonArray();
        JsonObject routeArray = routes.get(0).getAsJsonObject();
        JsonArray legs = routeArray.get("legs").getAsJsonArray();
        JsonObject legsArray = legs.get(0).getAsJsonObject();
        JsonObject duration = legsArray.get("duration").getAsJsonObject();

        return duration.get("text").getAsString();
    }

    public static void emailSetter() {

    }

}
