package com.shophurry;

public class Product {
    private final int id;
    private final String name;
    private final String store;
    private final int price;
    private final String[] tags;

    public Product(int id, String name, String store, int price, String... tags) {
        this.id = id;
        this.name = name;
        this.store = store;
        this.price = price;
        this.tags = tags;
    }
}
