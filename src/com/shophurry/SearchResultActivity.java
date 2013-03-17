package com.shophurry;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class SearchResultActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String queryString = getIntent().getExtras().getString("queryString");
        List<Product> products = Product.all;
        if (!queryString.trim().isEmpty()) {
            products = Product.search(queryString);
        }

        setContentView(R.layout.search_main_layout);
        ListView searchResults = ((ListView) findViewById(R.id.searchResults));
        searchResults.setAdapter(new ProductItem(this, products));
    }

    public static class ProductItem extends ArrayAdapter<Product> {
        private final Context context;
        private final List<Product> searchResults;

        public ProductItem(Context context, List<Product> searchResults) {
            super(context, R.layout.product_item, searchResults);
            this.context = context;
            this.searchResults = searchResults;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View productItemLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.product_item, parent, false);
            Product product = searchResults.get(position);

            ((ImageView) productItemLayout.findViewById(R.id.imageProduct)).setImageResource(product.image);
            ((TextView) productItemLayout.findViewById(R.id.textProductDesc)).setText(product.desc);
            ((TextView) productItemLayout.findViewById(R.id.textStore)).setText(product.brand);
            String distance = "\n(" + (1 + new Random().nextInt(5)) + "kms from here)";
            ((TextView) productItemLayout.findViewById(R.id.textLocation)).setText(product.address + distance);
            ((TextView) productItemLayout.findViewById(R.id.textPrice)).setText(Html.fromHtml("&#8377; " + product.price + "/-"));

            ((TextView) productItemLayout.findViewById(R.id.textMore)).setText(Html.fromHtml("<u>" + (2 + new Random().nextInt(5)) + " Similar Items in this store &gt;&gt;&gt;</u>"));

            return productItemLayout;
        }
    }
}