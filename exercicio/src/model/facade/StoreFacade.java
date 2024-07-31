package model.facade;

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

    }

    @Override
    public void createUser(String cpf, String name) {

    }

    @Override
    public void addBalance(String userCpf, double amount) {

    }

    @Override
    public void addProductToCart(String userCpf, int productId) {

    }

    @Override
    public void removeProductToCart(String userCpf, int productId) {

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
