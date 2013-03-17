package com.shophurry;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class ProductsTable {
    public static final String PRODUCTS = "products";
    public static final String PRODUCTS_NAME = "name";
    public static final String PRODUCTS_IMAGE = "image";
    public static final String PRODUCTS_ID = BaseColumns._ID;
    private final SQLiteDatabase database;

    public ProductsTable(SQLiteDatabase database) {
        this.database = database;
    }

    public List<Product> find(List<String> keywords) {
        String query = "";
        String[] params = {};
        Cursor productCursor = database.rawQueryWithFactory(new ProductCursor.Factory(), query, params, null);
        List<Product> productList = new ArrayList<Product>();
        if (productCursor != null && productCursor.moveToFirst()) {
            do {
                productList.add(((ProductCursor) productCursor).getProduct());
            } while (productCursor.moveToNext());
        }

        return productList;
    }

    public static class ProductCursor extends SQLiteCursor {
        public ProductCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
            super(driver, editTable, query);
        }

        public Product getProduct() {
            return null;
        }

        private static class Factory implements SQLiteDatabase.CursorFactory {
            @Override
            public Cursor newCursor(SQLiteDatabase sqLiteDatabase, SQLiteCursorDriver sqLiteCursorDriver, String editTable, SQLiteQuery sqLiteQuery) {
                return new ProductCursor(sqLiteCursorDriver, editTable, sqLiteQuery);
            }
        }

    }
}
