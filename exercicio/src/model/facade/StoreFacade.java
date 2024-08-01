package model.facade;

import model.entity.Notification;
import model.entity.Product;
import model.entity.User;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class StoreFacade implements IStoreFacade {

    private List<User> users;
    private List<Product> products;

    public StoreFacade() {
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

        var validatedUser = getUserByCpf(userCpf);

        if (validatedUser == null) {
            throw new InvalidParameterException("Usuário não encontrado");
        }

        if (validatedUser.getCpf().equals(userCpf)) {
            validatedUser.addBalance(amount);

            var title = "Saldo atualizado!";
            var message = "Seu saldo foi atualizado para " + validatedUser.getBalance();

            var notification = new Notification(title, message);
            validatedUser.addNotification(notification);
        }
    }

    @Override
    public void addProductToCart(String userCpf, int productId) {

    }

    @Override
    public void removeProductFromCart(String userCpf, int productId) {

    }

    @Override
    public void checkout(String userCpf) {

        var validatedUser = getUserByCpf(userCpf);

        if (validatedUser == null) {
            throw new InvalidParameterException("Usuário não encontrado");
        }
        else {
            var finalizedPurchase = validatedUser.checkout();
            validatedUser.addNotification(new Notification("Compra (" + finalizedPurchase.getId() + ") foi finalizada", "Compra finalizada com sucesso!"));
        }
    }

    @Override
    public String getNotificationsByUser(String userCpf) {

        StringBuilder sb = new StringBuilder();

        var validatedUser = getUserByCpf(userCpf);

        if (validatedUser == null) {
            throw new InvalidParameterException("Usuário não encontrado");
        }

        if (validatedUser.getNotifications().isEmpty()) {
            return "Nenhuma notificação";
        }

        for (Notification n : validatedUser.getNotifications()) {
            sb.append("-----------------------").append("\n");
            sb.append(n.getTitle()).append("\n");
            sb.append(n.getDescription()).append("\n");
            sb.append("-----------------------").append("\n");
        }

        return sb.toString();
    }

    @Override
    public String clearNotifications(String userCpf) {

        var validatedUser = getUserByCpf(userCpf);

        if (validatedUser == null) {
            throw new InvalidParameterException("Usuário não encontrado");
        }

        if (validatedUser.getNotifications().isEmpty()) {
            return "Nenhuma notificação";
        }
        else {
            validatedUser.clearNotifications();
            return "Notificações apagadas";
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    private User getUserByCpf(String cpf) {
        for (User user : users) {
            if (user.getCpf().equals(cpf)) {
                return user;
            }
        }
        return null;
    }
}
