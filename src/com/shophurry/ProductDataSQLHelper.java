package com.shophurry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Helper to the database, manages versions and creation
 */
public class ProductDataSQLHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "products.db";
    public static final int DATABASE_VERSION = 1;

    // Table name
    public static final String PRODUCTS = "products";
    public static final String PRODUCTS_NAME = "name";
    public static final String PRODUCTS_IMAGE = "image";
    public static final String PRODUCTS_ID = BaseColumns._ID;


    public ProductDataSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("com.shophurry.ProductDataSQLHelper", "Launching ProductDataSQLHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("com.shophurry.DataInsert", "Starting DataInsert");
        String productsSql = "create table if not exists " + PRODUCTS + "( " + PRODUCTS_ID + " integer primary key autoincrement, " +
                PRODUCTS_NAME + " string, " +
                PRODUCTS_IMAGE + " string);";
        Log.d("com.shophurry.DataInsert", "onCreate: " + productsSql);
        db.beginTransaction();
        try {
            db.execSQL(productsSql);
            insertData(db, "products.csv", "insert into products(name, image) values('%s','%s')");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        Log.d("com.shophurry.DataInsert", "Inserted Products Data successfully");
    }

    private void insertData(SQLiteDatabase db, String fileName, String sqlTemplate) {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        Log.d("com.shophurry.DataInsert", "Start Writing Data");

        try {
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                db.execSQL(String.format(sqlTemplate, data[0], data[1]));
                Log.d("com.shophurry.DataInsert", "Wrote " + data[0]);
            }
        } catch (IOException e) {
            Log.wtf("com.shophurry.DataInsert", "onInsert:", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}