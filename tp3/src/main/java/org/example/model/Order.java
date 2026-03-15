package org.example.model;

import lombok.Getter;
import org.example.policy.DiscountPolicy;
import org.example.service.EmailService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Order {
    private final Client client;
    private final List<Item> items;
    private final double discountRate;

    public Order(Client client, double discountRate) {
        this.client = client;
        this.discountRate = discountRate;
        this.items = new ArrayList<>();
    }

    public void addItem(String productName, int quantity, double price) {
        items.add(new Item(productName, quantity, price));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (Item item : items) {
            subtotal += item.calculateTotal();
        }
        return subtotal;
    }

    public double calculateDiscount() {
        return DiscountPolicy.calculateDiscount(calculateSubtotal(), discountRate);
    }

    public double calculateFinalTotal() {
        return calculateSubtotal() - calculateDiscount();
    }

    public void sendConfirmationEmail() {
        String message = createConfirmationMessage();
        EmailService.sendEmail(client.getEmail(), message);
    }

    private String createConfirmationMessage() {
        return "Pedido recebido! Obrigado pela compra.";
    }
}