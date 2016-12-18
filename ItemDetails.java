package com.example.yehuda.inventoryapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;
import java.security.SecureRandom;

import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.email;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.itemName;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.phone;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.sales;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.stock;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.tableName;

public class ItemDetails extends Activity {

    InventoryDBHelper controller = new InventoryDBHelper(this);
    private int i = 0;

    EditText ITEMNAME, SALES, STOCK, EMAIL, PHONENUMBER;
//    TextView infotext;

    String session = null;//////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ITEMNAME = (EditText) findViewById(R.id.itemName);
        SALES = (EditText) findViewById(R.id.sales);
        STOCK = (EditText) findViewById(R.id.stock);
        EMAIL = (EditText) findViewById(R.id.email);
        PHONENUMBER = (EditText) findViewById(R.id.phoneNumber);
//        infotext = (TextView) findViewById(R.id.txtresulttext);
        session = getIntent().getStringExtra("EXTRA_SESSION_ID");//////////////////////////////////////////////
        setDetails();/////////////
    }

    public void plus(View view) {
        i = Integer.parseInt(SALES.getText().toString());
        i++;
        SALES.setText(String.valueOf(i));
    }

    public void minus(View view) {
        i = Integer.parseInt(SALES.getText().toString());
        i--;
        SALES.setText(String.valueOf(i));
    }

    public String nextSessionId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public void update(View view) {
        controller = new InventoryDBHelper(getApplicationContext());
        SQLiteDatabase db = controller.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(phone, PHONENUMBER.getText().toString());
        values.put(email, EMAIL.getText().toString());
        values.put(sales, Integer.parseInt(SALES.getText().toString()));
//        values.put(idnum, nextSessionId());
        values.put(itemName, ITEMNAME.getText().toString());
        values.put(stock, Integer.parseInt(STOCK.getText().toString()));
        db.insert(tableName, null, values);
        db.close();
//        infotext.setText("items added Successfully");

        //go back to main screen
        startActivity(new Intent(ItemDetails.this, MainActivity.class));
    }

    // if coming from clicking on a specific item from the list
    public void setDetails() {
        controller = new InventoryDBHelper(getApplicationContext());
        SQLiteDatabase db = controller.getWritableDatabase();
        String query = "SELECT * FROM " + tableName + " WHERE " + itemName + " = "+ session;//
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                SALES.setText(cursor.getString(cursor.getColumnIndex(sales)));

                ITEMNAME.setText(session);////////////////////////
            }while (cursor.moveToNext());
        }
cursor.close();

    }
}
