package com.example.lab_10;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiThread extends Thread {

    MainActivity activity;

    public ApiThread(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        try {

            String apiUrl = "https://dummyjson.com/quotes/random";

            URL url = new URL(apiUrl);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream)
            );

            StringBuilder response = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                response.append(line);
            }

            reader.close();

            JSONObject quoteObject = new JSONObject(response.toString());

            String quote = quoteObject.getString("quote");
            //String author = quoteObject.getString("author");


            activity.runOnUiThread(() -> {
                activity.textViewQuote.setText(quote);
                //activity.textViewAuthor.setText(author);
            });

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
