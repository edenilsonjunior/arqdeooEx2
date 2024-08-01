package view;

public class MenuStoreView {

    public static int menuAdmin() {

        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("                     🌟 Menu Administrador 🌟                    ");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Adicionar produto");
        System.out.println("3 - Listar usuarios");
        System.out.println("4 - Listar produtos cadastrados no sistema");
        System.out.println("0 - Sair");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.print("Opção: ");
        return Integer.parseInt(System.console().readLine());
    }

    public static int menuUser() {

        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("                       🛒 Menu Usuário 🛒                        ");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("1 - Adicionar saldo");
        System.out.println("2 - Adicionar produto ao carrinho");
        System.out.println("3 - Remover produto do carrinho");
        System.out.println("4 - Finalizar compra");
        System.out.println("5 - Ver notificações");
        System.out.println("6 - Limpar notificações");
        System.out.println("7 - Listar produtos cadastrados no sistema");
        System.out.println("8 - Mostrar carrinho");
        System.out.println("9 - Listar compras efetuadas");
        System.out.println("0 - Sair");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.print("Opção: ");
        return Integer.parseInt(System.console().readLine());
    }

    public static void mainMenu(){
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("            🌟 Bem-vindo ao Sistema de E-Commerce 🌟             ");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("                                                                  ");
        System.out.println("    Navegue com facilidade, compre com confiança, e aproveite     ");
        System.out.println("           uma experiência incrível em nosso sistema!             ");
        System.out.println("                                                                  ");
        System.out.println("            👩‍💻 Desenvolvedores: Henrique e Edenilson 👨‍💻          ");
        System.out.println("                                                                  ");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("            🔐 Login no Sistema de E-Commerce 🔐                 ");
        System.out.println("══════════════════════════════════════════════════════════════════");
    }
}
