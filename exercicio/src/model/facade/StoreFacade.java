package model.facade;

import model.entity.Notification;
import model.entity.Product;
import model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StoreFacade implements  IStoreFacade{

    private List<User> users;
    private List<Product> products;

    public StoreFacade(){
        users = new ArrayList<>();
        products = new ArrayList<>();
    }


    @Override
    public void addProduct(String name, String description, double price) {
        var product = new Product(name, description, price);
        products.add(product);

        var title = "Novo produto!";
        var message = "o produto " + name + " foi adicionado ao sistema";
        var notification = new Notification(title, message);

        for (User user : users) {
            user.addNotification(notification);
        }
    }

    @Override
    public void createUser(String cpf, String name, String password) {

        User user = new User(cpf, name, password);

        var title = "Seja bem-vindo(a) " + name + "!";
        var message = "Você foi cadastrado(a) com sucesso!";

        var notification = new Notification(title, message);
        user.addNotification(notification);
    }


    @Override
    public void addBalance(String userCpf, double amount) {

    }

    @Override
    public void addProductToCart(String userCpf, int productId) {

    }

    @Override
    public void removeProductFromCart(String userCpf, int productId) {

    }

    @Override
    public void checkout(String cpfUser) {

    }

    @Override
    public void getNotificationsByUser(String userCpf) {

    }

    @Override
    public void clearNotifications(String userCpf) {

    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }
}
