package com.shophurry;


import android.database.sqlite.SQLiteDatabase;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductsTableTest extends TestCase {
    public void shouldSearchProductsBasedOnType() {
        SQLiteDatabase database = mock(SQLiteDatabase.class);
        ProductsTable table = new ProductsTable(database);

        ProductsTable.ProductCursor stubCursor = mock(ProductsTable.ProductCursor.class);
        when(stubCursor.moveToFirst()).thenReturn(true);
        when(stubCursor.getProduct()).thenReturn(new Product());
        when(stubCursor.moveToNext()).thenReturn(false);

        String sql = "select p.name from products p, tags t  where p._id = t._id and t.name like '%jeans%' OR t.name like '%blue%'";
        String[] params = {};
        when(database.rawQueryWithFactory(any(SQLiteDatabase.CursorFactory.class), eq(sql), aryEq(params), anyString()))
                .thenReturn(stubCursor);

        List<Product> products = table.find(new ArrayList<String>() {{
            add("Jeans");
            add("Blue");
        }});
        assertEquals(1, products.size());
    }
}
