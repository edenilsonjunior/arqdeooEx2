package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Buy {

    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private final int id;
    private final List<Product> items;

    public Buy() {
        this.id = idCounter.getAndIncrement();
        this.items = new ArrayList<>();
    }


    public void addItem(Product item) {

        Product p = new Product(item);
        this.items.add(p);
    }


    public void removeItem(Product item) {

        this.items.remove(item);
    }


    public double getTotalPrice() {
        double totalPrice = 0;

        for (Product item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }


    public int getId() {
        return this.id;
    }

    public List<Product> getItems() {
        return this.items;
    }
}
