package com.example.yehuda.testlistview;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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

import static android.content.ContentValues.TAG;

public class TestHTTP extends AsyncTask<String, Void, String> {

    private TextView texte;

    public TestHTTP(TextView textView) {
        texte = textView;
    }

    private JSONParser parser = new JSONParser();

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=lTrump&maxResults=1");
            urlConnection = (HttpURLConnection) url.openConnection();


            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            if (in != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line + "\n";
                }

                try {
                    Object obj = parser.parse(result);
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONArray items = (JSONArray) jsonObject.get("items");

                    Log.d(TAG, "items length: " + items.size());//for some reason is not more than 1
                    String name = "";
                    //the the following is supposed to return just the title of book
                    for (int i = 0; i < items.size(); i++) {
                        JSONObject item = (JSONObject) items.get(i);//if 'i' equals more then one program crashes
                        if(item.get("title")!=null) {//only get the title
                            JSONObject theTitle = (JSONObject) item.get("title");
                            name = (String) theTitle.get("title");
                        }
                    }
                    result = name;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            in.close();


            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result;

    }

    @Override
    protected void onPostExecute(String result) {
        texte.setText(result);
        super.onPostExecute(result);
    }
}

