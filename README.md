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
