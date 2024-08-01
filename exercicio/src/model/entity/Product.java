package model.entity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Product {

    private static final AtomicInteger idCounter = new AtomicInteger(0);
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

    public Product(Product p){
        this.id = p.id;
        this.name = p.name;
        this.description = p.description;
        this.price = p.price;
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

    @Override
    public String toString() {

        String sb = "ID.......: " + getId() + "\n" +
                "Nome.....: " + getName() + "\n" +
                "Descrição: " + getDescription() + "\n" +
                "Preço....: R$ " + String.format("%.2f", getPrice()) + "\n";
        return sb;
    }
}
