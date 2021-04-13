package com.example.madsqlite;

public class ProductModel {
    String productName;
    String productID;
    String productPrice;
    String productMRP;

    ProductModel(String name, String id, String price, String mrp) {
        productID = id;
        productName = name;
        productPrice = price;
        productMRP = mrp;
    }

}
