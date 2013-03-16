package com.shophurry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.shophurry.ProductDataSQLHelper.DATABASE_NAME;

/**
 * Helper to the database, manages versions and creation
 */
public class TagsDataSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TAGS = "tags";
    public static final String TAGS_PRODUCT_ID = "product_id";
    public static final String TAGS_NAME = "tag_name";

    public TagsDataSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("TagsDataSQLHelper", "Launching TagsDataSQLHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tagsSql = "create table if not exists " + TAGS + "( " +
                TAGS_PRODUCT_ID + " integer, " +
                TAGS_NAME + " string);";
        Log.d("DataInsert", "onCreate: " + tagsSql);
        db.execSQL(tagsSql);

        insertData(db, "tags.csv", "insert into tags(product_id, tag_name) values(%d,'%s')");
        Log.d("DataInsert", "Inserted Tags Data successfully");
    }

    private void insertData(SQLiteDatabase db, String fileName, String sqlTemplate) {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        try {
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                db.execSQL(String.format(sqlTemplate, data[0], data[1]));
            }
        } catch (IOException e) {
            Log.wtf("DataInsert", "onInsert:", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}