package com.example.yehuda.inventoryapp;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.dbName;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.email;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.idnum;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.itemName;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.phone;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.sales;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.stock;
import static com.example.yehuda.inventoryapp.DBContract.ItemEntry.tableName;

public class InventoryDBHelper extends SQLiteOpenHelper {

//    DBContract contract = new DBContract();

    public InventoryDBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + idnum + " integer primary key AUTOINCREMENT, " + itemName + " text, " +stock+ " integer, " + sales + " integer, " + email + " text, " + phone + " text)";
        database.execSQL(query);

//        insertData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS " + tableName;
        database.execSQL(query);
        onCreate(database);
    }

//    public void insertData() {
//
//        SQLiteDatabase database = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(idnum, 1);
//        values.put(itemName, "computer mouse");
//        values.put(stock, 3);
//        values.put(sales, 1);
//        values.put(email, "example@gmail.com");
//        values.put(phone, "4326254367");
//
//        database.insert(tableName, null, values);
//
//    }

    public ArrayList<HashMap<String, String>> getAllPlace() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + tableName + " ORDER BY " + itemName;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("itemName", cursor.getString(1));
                map.put("stock", cursor.getString(2));
                map.put("sales", cursor.getString(3));

                wordList.add(map);
            } while (cursor.moveToNext());
        }
        // return contact list
        return wordList;
    }


}