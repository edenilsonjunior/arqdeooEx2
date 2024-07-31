package model.entity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Product {

    private static AtomicInteger idCounter = new AtomicInteger(0);
    private final int id;
    private final String name;
    private final String description;
    private final double price;


    public Product(String name, String description, double price) {
        this.id = idCounter.getAndIncrement();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return  id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
