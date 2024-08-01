package model.facade;

import com.sun.jdi.connect.Connector;
import model.entity.Notification;
import model.entity.Product;
import model.entity.User;

import java.security.InvalidParameterException;
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
