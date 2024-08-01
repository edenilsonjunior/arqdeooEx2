package model.facade;

import enums.UserType;

public interface IStoreFacade {

    UserType login(String userCpf, String userPassword);
    void addProduct(String name, String description, double price);

    void createUser(String cpf, String name, String password);

    void addBalance(String userCpf, double amount);
    void addProductToCart(String userCpf, int productId);
    void removeProductFromCart(String userCpf, int productId);
    void checkout(String userCpf);

    String getCart(String userCpf);

    String getNotificationsByUser(String userCpf);
    String clearNotifications(String userCpf);

    String listProducts();
    String listUsers();
    String showCart(String userCpf);
    String showPurchases(String userCpf);
}
