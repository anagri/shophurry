package com.shophurry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helper to the database, manages versions and creation
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "products.db";
    public static final int DATABASE_VERSION = 1;
    public static final String LOG_TAG = "com.shophurry.DatabaseHelper";
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(LOG_TAG, "Creating Database");
        createAndPopulateTable(db, context.getString(R.string.products_table_sql), "products", "products.csv", "insert into products(name, image) values('%s','%s')");
        createAndPopulateTable(db, context.getString(R.string.tags_table_sql), "tags", "tags.csv", "insert into tags(_id, name) values(%d,'%s')");
    }

    private void createAndPopulateTable(SQLiteDatabase db, String tableSql, String tableName, String csvFile, String sqlTemplate) {
        Log.d(LOG_TAG, "Creating table " + tableName + ": " + tableSql);
        db.beginTransaction();
        try {
            db.execSQL(tableSql);
            insertData(db, csvFile, sqlTemplate);
            db.setTransactionSuccessful();
        } catch (IOException e) {
            Log.wtf(LOG_TAG, "onInsert:", e);
        } finally {
            db.endTransaction();
        }
        Log.d(LOG_TAG, "Inserted Products Data successfully");
    }

    private void insertData(SQLiteDatabase db, String fileName, String sqlTemplate) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));

        Log.d(LOG_TAG, "Start Writing Data");
        String[] data;
        int count = 0;
        while ((data = csvReader.readNext()) != null) {
            db.execSQL(String.format(sqlTemplate, data[0], data[1]));
            count++;
        }

        Log.d(LOG_TAG, "Finished writing " + count + " records from " + fileName + ".");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}