package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
    private final String productName;
    private final int quantity;
    private final double price;


    public double calculateTotal() {
        return quantity * price;
    }
}
