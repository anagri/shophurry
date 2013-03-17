package com.shophurry;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Random;

public class SearchResultActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main_layout);
        ListView searchResults = ((ListView) findViewById(R.id.searchResults));
        searchResults.setAdapter(new ProductItem(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static class ProductItem extends ArrayAdapter<Product> {
        private final Context context;

        public ProductItem(Context context) {
            super(context, R.layout.product_item, Product.all);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View productItemLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.product_item, parent, false);
            Product product = Product.all.get(position);

            ((ImageView) productItemLayout.findViewById(R.id.imageProduct)).setImageResource(product.image);
            ((TextView) productItemLayout.findViewById(R.id.textProductDesc)).setText(product.desc);
            ((TextView) productItemLayout.findViewById(R.id.textStore)).setText(product.brand);
            ((TextView) productItemLayout.findViewById(R.id.textLocation)).setText(product.address);
            ((TextView) productItemLayout.findViewById(R.id.textPrice)).setText(Html.fromHtml("&#8377; " + product.price + "/-"));
            ((TextView) productItemLayout.findViewById(R.id.textMore)).setText(Html.fromHtml("<u>" + (2 + new Random().nextInt(5)) + " Similar Items in this store &gt;&gt;&gt;</u>"));

            return productItemLayout;
        }
    }
}