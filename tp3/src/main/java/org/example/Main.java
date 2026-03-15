package org.example;

import org.example.interfaces.InvoicePrinter;
import org.example.model.Client;
import org.example.model.Order;

public class Main {


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
