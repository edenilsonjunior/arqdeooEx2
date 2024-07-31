package model.facade;

public interface IStoreFacade {

    public void addProduct(String name, String description, double price);

    public void createUser(String cpf, String name);
    public void addBalance(String userCpf, double amount);
    public void addProductToCart(String userCpf, int productId);
    public void removeProductToCart(String userCpf, int productId);
    public void checkout(String cpfUser);

    public void getNotificationsByUser(String userCpf);
    public void clearNotifications(String userCpf);
}
