package com.example.yehuda.inventoryapp;

import android.provider.BaseColumns;

public final class DBContract {
    public static final class ItemEntry implements BaseColumns {
        public static final String dbName = "inventoryDB";
        public static final String tableName = "inventoryTable";
        public static final String itemName = "itemName";
        public static final String stock = "stock";
        public static final String sales = "sales";
        public static final String email = "email";
        public static final String phone = "phone";
        public static final String idnum = "ID";
//        public static final Bitmap picture = com.example.yehuda.inventoryapp.R.mipmap.ic_launcher;
    }
}
