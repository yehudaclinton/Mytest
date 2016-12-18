package com.example.yehuda.inventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.itemName;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.sales;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.stock;


public class ItemList extends Activity {
    InventoryDBHelper controller = new InventoryDBHelper(this);
    ListView ls;
    TextView infotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ls = (ListView) findViewById(R.id.itemlist);
        infotext = (TextView) findViewById(R.id.txtresulttext);


        try {
            List<HashMap<String, String>> data = controller.getAllPlace();
            if (data.size() != 0) {
                SimpleAdapter adapter = new SimpleAdapter(
                        ItemList.this, data, R.layout.rows,
                        new String[]{itemName, stock, sales}, new int[]{
                        R.id.tvitemname, R.id.tvstock,
                        R.id.tvsales});

                ls.setAdapter(adapter);


                ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    //clicking on title to find more about the book
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        final TextView ITEMNAME = (TextView) view.findViewById(R.id.tvitemname);
                        Intent intent = new Intent(getBaseContext(), ItemDetails.class);
                        String session = ITEMNAME.getText().toString();
                        intent.putExtra("EXTRA_SESSION_ID", session);
                        startActivity(intent);

//                        //make url intent
//                        uri = Uri.parse(ItemLoader.urls[position]);//
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
                    }

                });

                String length = String.valueOf(data.size());
                infotext.setText(length + " items");
            } else {
                infotext.setText("No data in database");
            }

        } catch (Exception ex) {
            infotext.setText(ex.getMessage().toString());
        }
    }


}
