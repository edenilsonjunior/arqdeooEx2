package model.facade;

public interface IStoreFacade {

    public void addProduct(String name, String description, double price);

    public void createUser(String cpf, String name, String password);

    public void addBalance(String userCpf, double amount);
    public void addProductToCart(String userCpf, int productId);
    public void removeProductFromCart(String userCpf, int productId);
    public void checkout(String userCpf);

    public String getNotificationsByUser(String userCpf);
    public String clearNotifications(String userCpf);
}
