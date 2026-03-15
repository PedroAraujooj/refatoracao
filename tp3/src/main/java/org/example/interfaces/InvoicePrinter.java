package org.example.interfaces;

import org.example.model.Item;
import org.example.model.Order;

public class InvoicePrinter {
    public void print(Order order) {
        printClient(order);
        printItems(order);
        printSummary(order);
    }

    private void printClient(Order order) {
        System.out.println("Cliente: " + order.getClient().getName());
    }

    private void printItems(Order order) {
        for (Item item : order.getItems()) {
            printItemLine(item);
        }
    }

    private void printItemLine(Item item) {
        System.out.println(item.getQuantity() + "x " + item.getProductName() + " - R$" + item.getPrice());
    }

    private void printSummary(Order order) {
        System.out.println("Subtotal: R$" + order.calculateSubtotal());
        System.out.println("Desconto: R$" + order.calculateDiscount());
        System.out.println("Total final: R$" + order.calculateFinalTotal());
    }
}
