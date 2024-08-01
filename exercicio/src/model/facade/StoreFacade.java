package model.facade;

import model.entity.*;
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

        return users.stream()
                .filter(user -> user.getCpf().equals(userCpf) && user.getPassword().equals(userPassword))
                .map(user -> UserType.CLIENT)
                .findFirst()
                .orElse(UserType.NOT_AUTHORIZED);
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

        validatedUser.addBalance(amount);

        var title = "Saldo atualizado!";
        var message = "Seu saldo foi atualizado para " + validatedUser.getBalance();
        sendNotification(validatedUser, title, message);
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
            sb.append(p).append("\n");
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

        sb.append("Notificações de ").append(validatedUser.getName()).append("\n");
        for (Notification n : validatedUser.getNotifications()) {
            sb.append(n).append("\n");
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

        if (products.isEmpty()) {
            return "Nenhum produto cadastrado";
        }
      
        StringBuilder sb = new StringBuilder();

        sb.append("═════════════════════════════════════════").append("\n");
        sb.append("        📦 Detalhes do Produto 📦        ").append("\n");
        sb.append("═════════════════════════════════════════").append("\n");
        for (Product p : products) {
            sb.append(p);
            sb.append("-----------------------").append("\n");
        }
        return sb.toString();
    }


    @Override
    public String listUsers() {

        if (users.isEmpty()) 
            return "Nenhum usuário cadastrado";
        
        StringBuilder sb = new StringBuilder();

        for (User u : users) {
            sb.append("-----------------------").append("\n");
            sb.append(u).append("\n");
            sb.append("-----------------------").append("\n");
        }

        return sb.toString();
    }

    @Override
    public String showCart(String userCpf){

        var validatedUser = getUserByCpf(userCpf);

        if (validatedUser == null) 
            throw new InvalidParameterException("Usuário não encontrado");
        
        if (validatedUser.getCart().getItems().isEmpty()) 
            return "Carrinho vazio";
        

        var sb = new StringBuilder();

        sb.append("Carrinho de compras de ").append(validatedUser.getName()).append("\n");
        sb.append(validatedUser.getCart()).append("\n");
        return sb.toString();
    }

    public String showPurchases(String userCpf){
            
            var validatedUser = getUserByCpf(userCpf);
    
            if (validatedUser == null) 
                throw new InvalidParameterException("Usuário não encontrado");
            
            if (validatedUser.getPurchases().isEmpty()) 
                return "Nenhuma compra realizada";
            
            var sb = new StringBuilder();
    
            sb.append("Compras de ").append(validatedUser.getName()).append("\n");
            for (Buy b : validatedUser.getPurchases()) {

                sb.append("-----------------------").append("\n");
                sb.append(b).append("\n");
                sb.append("-----------------------").append("\n");
            }
    
            return sb.toString();
    }


    private User getUserByCpf(String cpf){

        return users.stream()
                .filter(user -> user.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    private Product getProductById(int id){

        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private Product getProductByUser(User user, int productId){

        return user.getCart().getItems().stream()
                .filter(product -> product.getId() == productId)
                .findFirst()
                .orElse(null);
    }

    private void sendNotification(User user, String title, String message){
        var notification = new Notification(title, message);
        user.addNotification(notification);
    }
}
