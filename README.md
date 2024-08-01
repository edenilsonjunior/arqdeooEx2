# arqdeooEx2

## Alunos
Henrique e Edenilson

## Descrição do Projeto
O projeto "arqdeooEx2" é um sistema de gerenciamento de loja online que permite a interação entre usuários e produtos através de uma interface centralizada. O sistema inclui funcionalidades como login de usuários, gerenciamento de produtos, adição e remoção de itens do carrinho de compras, e processamento de pagamentos.

## Problema Solucionado pelos Design Patterns
O principal problema solucionado pelos design patterns escolhidos neste projeto é a complexidade da interação entre diferentes componentes do sistema. Sem uma estrutura adequada, a comunicação entre as diversas partes do sistema, como a gestão de produtos, o gerenciamento de usuários e o carrinho de compras, poderia se tornar desordenada e difícil de manter.

Para resolver esse problema, foi escolhido design patterns Facade:
- Facade Pattern: Simplifica a interface do sistema, fornecendo uma interface única para interagir com múltiplas funcionalidades subjacentes.

## Facade Pattern
O Facade Pattern é utilizado para fornecer uma interface simplificada e coesa para as operações complexas do sistema. Ele permite que os clientes interajam com o sistema sem precisar conhecer os detalhes internos.

## Implementação e Contribuição
O `IStoreFacade` é a principal interface que utiliza o Facade Pattern. Ela encapsula a complexidade das operações do sistema e oferece métodos simplificados como `addProduct, createUser, checkout,` entre outros. Com isso, o cliente pode realizar operações básicas de forma direta e intuitiva, sem precisar se preocupar com a implementação interna e lógica de negócio dessas operações.

## Uml
![image](https://github.com/user-attachments/assets/6c2f5d06-b5b6-4da1-bd2d-d2c7f70d8e9a)
### View
![image](https://github.com/user-attachments/assets/8d3fe926-10a0-4d03-b0fb-1eb30a775063)
### Controller
![image](https://github.com/user-attachments/assets/24f5831f-12a0-459d-865e-39c38022c56b)
### Model
![image](https://github.com/user-attachments/assets/0e9bdbfa-3e3e-4f9b-93c6-58eb6e546d34)
### Enums
![image](https://github.com/user-attachments/assets/64c7cbb0-6dfe-407a-933c-75b5e8ecb181)
