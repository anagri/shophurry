package com.shophurry;

import java.util.ArrayList;

public class Products {

    private final ArrayList<Product> products;

    public Products() {
        products = new ArrayList<Product>() {{
            add(new Product(1,"Lowrise Skinny","Levi's jeans", 1180, "jeans", "levi", "blue", "low", "rise", "skinny"));
            add(new Product(2,"Straight Fit","Sunny's Store", 1199, "jeans", "levi", "blue", "straight", "fit"));
            add(new Product(3,"Slim Fit","Wrangler", 1200,  "jeans", "levi", "blue", "slim", "fit", "wrangler"));
            add(new Product(4,"Regular Fit","Flying Machine", 1180, "jeans", "levi", "blue", "regular", "fit"));
            add(new Product(5,"Narrow Fit","United Color of Benetton", 1180, "jeans", "levi", "blue", "narrow", "fit"));
            add(new Product(6,"Regular Fit","Lee", 1200, "jeans", "levi", "blue", "regular", "fit"));
            add(new Product(7,"Narrow Fit","Wrangler", 1230, "jeans", "levi", "blue", "narrow", "fit"));
            add(new Product(8,"Straight Fit","Lakshmi Garments", 1250,  "jeans", "levi", "blue", "straight", "fit"));
            add(new Product(9,"Skinny Fit","Sunny's Store", 1195, "jeans", "levi", "blue", "skinny", "fit"));
            add(new Product(10,"Narrow Fit","Lakshmi Garments", 1185, "jeans", "levi", "blue", "narrow", "fit"));
            add(new Product(11,"Regular Fit","Flying Machine", 1300, "jeans", "levi", "black", "regular", "fit"));
            add(new Product(12,"Narrow Fit","United Color of Benetton", 1500, "jeans", "levi", "black", "narrow"));
            add(new Product(13,"Straight Fit","Lakshmi Garments", 1800, "jeans", "levi", "grey", "straight", "fit"));
            add(new Product(14,"Skinny Fit","Sunny's Store", 1900, "jeans", "levi", "red", "skinny", "fit"));
            add(new Product(15,"Narrow Fit","Lakshmi Garments", 1100, "jeans", "levi", "green", "narrow", "fit"));
        }};
    }
}
