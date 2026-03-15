package org.example;

import org.example.interfaces.InvoicePrinter;
import org.example.model.Client;
import org.example.model.Order;

public class Main {


    /*
    *
    * 1. Encapsular registros e coleções

Antes, os campos eram public e acessados diretamente, agora são privados e acessador a partir de getters
*E agora conta com construtores bem definidos, evitando alteração externa indevida. E em Order
* o acesso aos itens é feito por getItems(), que retorna Collections.unmodifiableList(items), ou seja, uma lista imutavel
evitando alteração externa indevida.
    *
    *
    * 2. Substituir primitivos por objetos com responsabilidade
ANtes, haviam listas paralelas, que mantiam o Itens por ordem. agora foi feito um objeto
* e no Order há uma List com esses objetos. Esse obeto, item tem :product,quantitie,price
Isso elimina o risco de inconsistência entre listas de tamanhos diferentes.
    *
    *
    * 3. Ocultar delegados para reduzir acoplamento
Antes, a classe Order dependia diretamente de detalhes espalhados no código.
Agora, o envio de e-mail é dentro de Order, via método:
order.sendConfirmationEmail();
Ou seja, quem usa Order não precisa saber como o envio acontece.
    *
    *
    *
    *4. Mover funções entre classes para redistribuir responsabilidades

As responsabilidades foram separadas assim:
Order: controla pedido, itens e cálculos
Item: representa item e sabe calcular seu total
Client: representa cliente
InvoicePrinter: cuida da impressão da fatura
DiscountPolicy: calcula desconto
EmailService: envia e-mail
    *
    *
    *
    * 5. Reposicionar campos em classes apropriadas

Os dados:
nome
e-mail
foram movidos para a classe Client.
Tornado o modelo mais reutilizável e organizado.
    *
    *
    *
    * Na impressão da fatura, trechos foram extraídos em métodos privados:

printClient
printItems
printItemLine
printSummary

E no pedido:
createConfirmationMessage

Melhorando a legibilidade e manutenção.
    *
    *
    * 7. Remover código morto e proteger o sistema contra inconsistências

As principais melhorias foram:
remoção das listas paralelas, substituidas por lista de objetos
remoção de exposição direta de atributos, ou seja, encapsulamento
inicialização segura da lista no construtor
*
retorno imutável da coleção

separação das responsabilidades para reduzir duplicação e fragilidade
    *
    *
    * Resumo:
    * A refatoração  buscou melhorar a qualidade do sistema sem
    * alterar sua lógica de negócio. Os atributos públicos foram encapsulados,
    * as listas paralelas foram substituídas por uma List<> de objetos Item,
    *  e os dados do cliente foram movidos para uma classe própria chamada Client.
    * Além disso, as responsabilidades foram redistribuídas entre classes mais coesas,
    * como InvoicePrinter para impressão e DiscountPolicy para cálculo d
    * e desconto. Também foram extraídos métodos privados para reduzir blocos grandes
    *  e tornar o código mais legível, e seguro contra inconsistências.
    * */
    public static void main(String[] args) {
            Client client = new Client("João", "joao@email.com");
            Order order = new Order(client, 0.1);

            order.addItem("Notebook", 1, 3500.0);
            order.addItem("Mouse", 2, 80.0);

            InvoicePrinter printer = new InvoicePrinter();
            printer.print(order);

            order.sendConfirmationEmail();
        }
    }
