package model.entity;

import java.util.List;
import java.util.ArrayList;

public class User {

    private final String cpf;
    private final String name;
    private final String password;
    private double balance;
    private Buy cart;
    private List<Buy> purchases;
    private List<Notification> notifications;

    public User(String cpf, String name, String password) {
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.balance = 0.0;

        this.cart = new Buy();
        this.purchases = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }


    public void addBalance(double value) {

        if(value < 0) {
            throw new IllegalArgumentException("O valor deve ser positivo!");
        }

        this.balance += value;
    }

    public void addItemToCart(Product item) {
        this.cart.addItem(item);
    }

    public Product removeItemFromCart(int productId) {
        for (Product item : this.cart.getItems()) {
            if (item.getId() == productId) {
                this.cart.removeItem(item);
                return item;
            }
        }
        throw new IllegalArgumentException("Produto não encontrado no carrinho!");
    }

    public Buy checkout() {

         if(this.cart.getItems().isEmpty())
            throw new IllegalArgumentException("Carrinho de compras vazio!");

        if(this.cart.getTotalPrice() > this.balance)
            throw new IllegalArgumentException("Saldo insuficiente!");


        this.balance -= this.cart.getTotalPrice();

        Buy buy = this.cart;
        this.cart = new Buy();
        this.purchases.add(buy);
        return buy;
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    public void clearNotifications() {
        this.notifications.clear();
    }



    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Buy getCart() {
        return cart;
    }

    public List<Buy> getPurchases() {
        return purchases;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public String getPassword() {
        return password;
    }
}
