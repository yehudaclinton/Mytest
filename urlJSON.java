package com.example.yehuda.bookfinder;

import android.os.AsyncTask;
import android.widget.ListView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class urlJSON extends AsyncTask<String, Void, String> {

    private ListView listOfBooks;
    private String book;
    private JSONParser parser = new JSONParser();
//////////////////////////////////////////////////////////////////////////
    public String aBook;
    ////////////////////////////////////////////////////////////////////
    public urlJSON(ListView list, String newBook) {
        listOfBooks = list;
        book = newBook;
    }

    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=l" + book);//Trump
            urlConnection = (HttpURLConnection) url.openConnection();


            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            if (in != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line + "\n";//
                }

                try {
                    Object obj = parser.parse(result);
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONArray items = (JSONArray) jsonObject.get("items");
                    JSONObject first = (JSONObject) items.get(0);
                    JSONObject volumeInfo = (JSONObject) first.get("volumeInfo");
                    String name = (String) volumeInfo.get("title");

                    result = name;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result;

    }

    protected void onPostExecute(String result) {
        aBook = result;
        super.onPostExecute(result);

//        Intent data = new Intent();
//        data.putExtra("myobj", value);
//        setResult(MainActivity, data);

    }

}
