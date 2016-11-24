package com.example.yehuda.bookfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ListView listItemView;
    public String book1 = urlJSON.aBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void populateList(View view){

        String someText = new urlJSON(listItemView, input).execute().get();
        
        listItemView = (ListView) findViewById(R.id.bookListView);
        String[] listItemsValue = new String[]{book1};

        TextView textview = (TextView) findViewById(R.id.TV1);
        EditText inputText = (EditText) findViewById(R.id.ED);
        textview.setText(inputText.getText());
        String input = String.valueOf(inputText.getText());

        new urlJSON(listItemView, input).execute();

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listItemsValue);
//
//        listItemView.setAdapter(adapter);
//
//        listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

//clicking on title to find more about the book
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, listItemsValue[position], Toast.LENGTH_SHORT).show();
//                if (listItemsValue[position] == "PHP") {
//                    new TestHTTP(textView, "PHP").execute();
//                }
//            }
//
//        });
    }
}
