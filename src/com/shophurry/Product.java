package com.shophurry;

import java.util.ArrayList;
import java.util.List;

public class Product {
    public static final List<Product> all = new ArrayList<Product>() {{
        add(new Product(1, "Levi's jeans", "Lowrise Skinny", "Laxmi Garments, Koramangala", R.drawable.blue1, 1180, "jeans", "levi", "blue", "low", "rise", "skinny"));
        add(new Product(2, "Sunny's Store", "Straight Fit", "Ganesh Complex, 2nd block Koramangala", R.drawable.blue2, 1199, "jeans", "levi", "blue", "straight", "fit"));
        add(new Product(3, "Wrangler", "Slim Fit", "Shashi Stores, Domlur", R.drawable.blue3, 1200, "jeans", "levi", "blue", "slim", "fit", "wrangler"));
        add(new Product(4, "Flying Machine", "Regular Fit", "Flying Machine, 1st block, Koramangala", R.drawable.blue4, 1180, "jeans", "levi", "blue", "regular", "fit"));
        add(new Product(5, "United Color of Benetton", "Narrow Fit", "UCB Stores, BTM Layout", R.drawable.blue5, 1180, "jeans", "levi", "blue", "narrow", "fit"));
        add(new Product(6, "Lee", "Regular Fit", "Lee Jeans, 3rd block, Koramangala", R.drawable.blue6, 1200, "jeans", "levi", "blue", "regular", "fit"));
        add(new Product(7, "Wrangler", "Narrow Fit", "Raheja Stores, 2nd block, Koramanagala", R.drawable.blue7, 1230, "jeans", "levi", "blue", "narrow", "fit"));
        add(new Product(8, "Wrangler", "Straight Fit", "Wrangler, 6th block, Koramangala", R.drawable.blue8, 1250, "jeans", "levi", "blue", "straight", "fit"));
        add(new Product(9, "United Color of Benetton", "Skinny Fit", "New Age Garments, Ejipura", R.drawable.blue9, 1195, "jeans", "levi", "blue", "skinny", "fit"));
        add(new Product(10, "Flying Machine", "Narrow Fit", "Jealous 21, Indiranagar", R.drawable.jean1, 1185, "jeans", "levi", "black", "narrow", "fit"));
        add(new Product(11, "Flying Machine", "Regular Fit", "Flying Machine, Koramangala", R.drawable.jean1, 1300, "jeans", "levi", "black", "regular", "fit"));
        add(new Product(12, "United Color of Benetton", "Narrow Fit", "Ramesh Garments, 7th block, Koramangala", R.drawable.jean3, 1500, "jeans", "levi", "green", "narrow"));
        add(new Product(13, "Lakshmi Garments", "Straight Fit", "Joseph's Garments, Bommanahalli", R.drawable.jean4, 1800, "jeans", "levi", "grey", "straight", "fit"));
        add(new Product(14, "Sunny's Store", "Skinny Fit", "Gurpreet Garments, Kodihalli", R.drawable.jean4, 1900, "jeans", "levi", "grey", "skinny", "fit"));
        add(new Product(15, "Lakshmi Garments", "Narrow Fit", "Suresh Garments, 5th block, Koramangala", R.drawable.jean3, 1100, "jeans", "levi", "green", "narrow", "fit"));
        add(new Product(1, "Adidas", "Running Shoes", "Adidas Stores, Koramangala", R.drawable.run1, 3500, "shoes", "white"));
        add(new Product(2, "Puma", "Running Shoes", "Puma Stores, Koramangala", R.drawable.run2, 3999, "sports"));
        add(new Product(3, "Reebok", "Running Shoes", "Reebok Stores, Koramangala", R.drawable.run3, 3800, "shoes", "green"));
        add(new Product(4, "Nike", "Running Shoes", "Nike Stores, Koramangala", R.drawable.run4, 3750, "nike", "shoes", "white"));
        add(new Product(5, "Fila", "Running Shoes", "Fila Stores, Koramangala", R.drawable.run1, 3680, "shoes", "white", "blue"));
        add(new Product(6, "Reebok", "Running Shoes", "Reebok Stores, Indiranagar", R.drawable.run2, 3980, "shoes", "white"));
        add(new Product(7, "Puma", "Running Shoes", "Puma Stores, Indiranagar", R.drawable.run5, 3565, "shoes", "black", "nike"));
        add(new Product(8, "Reebok", "Running Shoes", "Reebok Stores, Indirangar", R.drawable.run6, 3856, "shoes", "red"));
        add(new Product(9, "Nike", "Running Shoes", "Nike Stores, Indiranagar", R.drawable.run7, 4200, "shoes", "black"));
        add(new Product(10, "Red Tape", "Dress Shoes", "Red Tape Stores, Koramangala", R.drawable.d1, 1200, "formal", "black", "shoes"));
        add(new Product(11, "Mochi", "Dress Shoes", "Mochi, Koramangala", R.drawable.d2, 2100, "formal", "brown", "shoes"));
        add(new Product(12, "Puma", "Sneakers", "Puma Stores, BTM Layout", R.drawable.s1, 2037, "shoes", "purple"));
        add(new Product(13, "Liberty", "Sneakers", "Liberty, Indiranagar", R.drawable.s2, 2300, "shoes", "black", "fun"));
        add(new Product(14, "Nike", "Sneakers", "Nike, BTM Layout", R.drawable.s3, 1278, "shoes", "brown", "fun"));
        add(new Product(15, "Puma", "Sneakers", "Puma, Koramangala", R.drawable.s4, 1800, "shoes", "blue"));
    }};
    public final int id;
    public final String brand;
    public final String desc;
    public final String address;
    public final int image;
    public final int price;
    public final String[] tags;

    public Product(int id, String brand, String desc, String address, int image, int price, String... tags) {
        this.id = id;
        this.brand = brand;
        this.desc = desc;
        this.address = address;
        this.image = image;
        this.price = price;
        this.tags = tags;
    }

    public static List<Product> search(String queryString) {
        String[] terms = queryString.split(" ");
        ArrayList<Product> results = new ArrayList<Product>();
        for (Product product : all) {
            if (product.isRelevantTo(terms)) {
                results.add(product);
            }
        }
        return results;
    }

    private boolean isRelevantTo(String[] terms) {
        for (String term : terms) {
            term = term.trim();

            if (term.isEmpty()) continue;

            for (String tag : tags) {
                if (term.contains(tag)) return true;
            }
        }
        return false;
    }
}
