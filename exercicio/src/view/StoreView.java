package view;

import controller.StoreController;
import enums.UserType;

public class StoreView {

    private int option;
    private final StoreController storeController;

    public StoreView() {
        this.option = 0;
        this.storeController = new StoreController();
    }

    public void run() {

        do {
            var loginData = login();
            var type = storeController.login(loginData[0], loginData[1]);

            if (type == UserType.ADMIN) {
                adminOptions();
            }
            else if (type == UserType.CLIENT) {
                clientOptions(loginData[0]);
            }
            else {
                System.out.println("Usuário não autorizado!");
            }

            option = Integer.parseInt(readString("Deseja continuar? 1-Sim | 0-Nao"));

        } while (option != 0);
    }

    private void adminOptions(){

        do {
            option = MenuStoreView.menuAdmin();

        switch (option)
        {
            case 1:
                var userCpf = readString("Digite o CPF do usuário........: ");
                var userName = readString("Digite o Nome do usuário.......: ");
                var userPassword = readString("Digite a Senha do usuário......: ");

                showMessage(storeController.createUser(userCpf, userName, userPassword));
                break;
            case 2:
                var productName = readString("Digite o Nome do produto.......: ");
                var productDescription = readString("Digite a Descricao do produto..: ");
                var productValue = Double.parseDouble(readString("Digite o Valor do produto......: "));

                showMessage(storeController.addProduct(productName, productDescription, productValue));
                break;
            case 3:
                showMessage(storeController.listProducts());
                break;
            case 0:
                return;
            default:
                showMessage("Opção inválida!");
                break;
        }
        } while (option != 0);
    }

    private void clientOptions(String cpf) {

        do {
            option = MenuStoreView.menuUser();

            switch (option) {
                case 1:
                    var value = Double.parseDouble(readString("Digite o valor a ser adicionado: "));
                    showMessage(storeController.addBalance(cpf, value));
                    break;
                case 2:
                    var id = Integer.parseInt(readString("Digite o ID do produto.........: "));
                    showMessage(storeController.addProductToCart(cpf, id));
                    break;
                case 3:
                    var idProduct = Integer.parseInt(readString("Digite o ID do produto.........: "));
                    showMessage(storeController.removeProductFromCart(cpf, idProduct));
                    break;
                case 4:
                    showMessage(storeController.checkout(cpf));
                    break;
                case 5:
                    showMessage(storeController.getNotificationsByUser(cpf));
                    break;
                case 6:
                    showMessage(storeController.clearNotifications(cpf));
                    break;
                case 7:
                    showMessage(storeController.listProducts());
                    break;
                case 0:
                    showMessage("Saindo...");
                    break;
                default:
                    showMessage("Opção inválida!");
                    break;
            }
        }
        while (option != 0);
    }


    private String[] login() {

        MenuStoreView.mainMenu();

        var cpf = readString("Digite seu CPF (somente números): ");
        var password = readString("Digite sua senha: ");
        return new String[]{cpf, password};
    }

    public static String readString(String str){
        showMessage(str);
        return System.console().readLine();
    }

    public static void showMessage(String message){
        System.out.println(message);
    }

    public static void showMessage(){
        System.out.println();
    }
}
