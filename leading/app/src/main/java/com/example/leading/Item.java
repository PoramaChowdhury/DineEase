package com.example.leading;

public class Item {
    private final int img;
    private final String name;
    private final String price;
    private final String description;

    public Item(int img,String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImg() {
        return img;
    }
}

