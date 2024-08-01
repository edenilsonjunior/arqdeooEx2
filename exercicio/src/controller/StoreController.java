package controller;

import enums.UserType;
import model.facade.StoreFacade;

public class StoreController {

    private final StoreFacade storeFacade;

    public StoreController() {
        this.storeFacade = new StoreFacade();
    }


    // -- User methods
    public String createUser(String cpf, String name, String password) {

        try{
            storeFacade.createUser(cpf, name, password);
            return "Usuário criado com sucesso!";

        } catch (Exception e){return e.getMessage();}
    }

    public UserType login(String userCpf, String userPassword) {
        return storeFacade.login(userCpf, userPassword);
    }

    public String addBalance(String userCpf, double amount) {
        try{
            storeFacade.addBalance(userCpf, amount);
            return "Saldo adicionado com sucesso!";

        } catch (Exception e){return e.getMessage();}
    }

    public String getNotificationsByUser(String userCpf) {
        try{
            return storeFacade.getNotificationsByUser(userCpf);

        } catch (Exception e){return e.getMessage();}
    }

    public String clearNotifications(String userCpf) {
        try{
            storeFacade.clearNotifications(userCpf);
            return "Notificações limpas com sucesso!";

        } catch (Exception e){return e.getMessage();}
    }


    // -- User Buy methods

    public String addProductToCart(String userCpf, int productId) {
        try{
            storeFacade.addProductToCart(userCpf, productId);
            return "Produto adicionado ao carrinho com sucesso!";

        } catch (Exception e){return e.getMessage();}
    }

    public String removeProductFromCart(String userCpf, int productId) {
        try{
            storeFacade.removeProductFromCart(userCpf, productId);
            return "Produto removido do carrinho com sucesso!";

        } catch (Exception e){return e.getMessage();}
    }

    public String checkout(String cpfUser) {
        try{
            storeFacade.checkout(cpfUser);
            return "Compra realizada com sucesso!";

        } catch (Exception e){return e.getMessage();}
    }



    // - Store methods
    public String addProduct(String name, String description, double price) {
        try{
            storeFacade.addProduct(name, description, price);
            return "Produto adicionado com sucesso!";

        } catch (Exception e){return e.getMessage();}
    }

    public String listProducts() {
        return storeFacade.listProducts();
    }

    public String listUsers() {
        return storeFacade.listUsers();
    }

}
