package model.facade;

import model.entity.Notification;
import model.entity.Product;
import model.entity.User;
import enums.UserType;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class StoreFacade implements IStoreFacade {

    private final List<User> users;
    private final List<Product> products;

    public StoreFacade() {
        users = new ArrayList<>();
        products = new ArrayList<>();
    }

    @Override
    public UserType login(String userCpf, String userPassword) {

        if(userCpf.equals("admin") && userPassword.equals("admin")){
            return UserType.ADMIN;
        }

        for (User user : users) {
            if(user.getCpf().equals(userCpf) && user.getPassword().equals(userPassword)){
                return UserType.CLIENT;
            }
        }

        return UserType.NOT_AUTHORIZED;
    }

    @Override
    public void addProduct(String name, String description, double price) {

        var product = new Product(name, description, price);
        products.add(product);

        var title = "Novo produto!";
        var message = "o produto " + name + " foi adicionado ao sistema";

        for (User user : users) {
            sendNotification(user, title, message);
        }
    }


    @Override
    public void createUser(String cpf, String name, String password) {

        if(getUserByCpf(cpf) != null)
            throw new InvalidParameterException("Usuário já cadastrado");

        User user = new User(cpf, name, password);
        users.add(user);

        var title = "Seja bem-vindo(a) " + name + "!";
        var message = "Você foi cadastrado(a) com sucesso!";
        sendNotification(user, title, message);
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

        User user = getUserByCpf(userCpf);
        Product product = getProductById(productId);

        if(user == null)
            throw new InvalidParameterException("Usuário não encontrado");

        if(product == null)
            throw new InvalidParameterException("Produto não encontrado");

        user.getCart().addItem(product);

        var title = "Novo produto no carrinho!";
        var message = "o produto " + product.getName() + " foi adicionado ao carrinho";
        sendNotification(user, title, message);
    }


    @Override
    public void removeProductFromCart(String userCpf, int productId) {

        User user = getUserByCpf(userCpf);

        if(user == null)
            throw new InvalidParameterException("Usuário não encontrado");


        Product productToRemove = getProductByUser(user, productId);

        if(productToRemove == null)
            throw new InvalidParameterException("Produto não encontrado no carrinho");

        user.getCart().removeItem(productToRemove);

        var title = "Produto removido do carrinho!";
        var message = "o produto:" + productToRemove.getName() + "foi removido do carrinho";
        sendNotification(user, title, message);
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
    public String getCart(String userCpf) {

        var validatedUser = getUserByCpf(userCpf);

        if (validatedUser == null) {
            throw new InvalidParameterException("Usuário não encontrado");
        }

        if (validatedUser.getCart().getItems().isEmpty()) {
            return "Carrinho vazio";
        }

        StringBuilder sb = new StringBuilder();

        for (Product p : validatedUser.getCart().getItems()) {
            sb.append("-----------------------").append("\n");
            sb.append("ID: ").append(p.getId()).append("\n");
            sb.append("Nome: ").append(p.getName()).append("\n");
            sb.append("Descrição: ").append(p.getDescription()).append("\n");
            sb.append("Preço: ").append(p.getPrice()).append("\n");
            sb.append("-----------------------").append("\n");
        }

        return sb.toString();
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


    @Override
    public String listProducts() {

        StringBuilder sb = new StringBuilder();

        if (products.isEmpty()) {
            return "Nenhum produto cadastrado";
        }

        for (Product p : products) {
            sb.append("-----------------------").append("\n");
            sb.append("ID: ").append(p.getId()).append("\n");
            sb.append("Nome: ").append(p.getName()).append("\n");
            sb.append("Descrição: ").append(p.getDescription()).append("\n");
            sb.append("Preço: ").append(p.getPrice()).append("\n");
            sb.append("-----------------------").append("\n");
        }

        return sb.toString();
    }


    @Override
    public String listUsers() {

        StringBuilder sb = new StringBuilder();

        if (users.isEmpty()) {
            return "Nenhum usuário cadastrado";
        }

        for (User u : users) {
            sb.append("-----------------------").append("\n");
            sb.append("CPF: ").append(u.getCpf()).append("\n");
            sb.append("Nome: ").append(u.getName()).append("\n");
            sb.append("Saldo: ").append(u.getBalance()).append("\n");
            sb.append("-----------------------").append("\n");
        }

        return sb.toString();
    }


    private User getUserByCpf(String cpf){
        for (User user : users) {
            if(user.getCpf().equals(cpf)){
                return user;
            }
        }
        return null;
    }

    private Product getProductById(int id){
        for (Product product : products) {
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    private Product getProductByUser(User user, int productId){
        for (Product product : user.getCart().getItems()) {
            if(product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    private void sendNotification(User user, String title, String message){
        var notification = new Notification(title, message);
        user.addNotification(notification);
    }
}
