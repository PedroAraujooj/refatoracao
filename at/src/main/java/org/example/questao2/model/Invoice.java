package org.example.questao2.model;

import lombok.Getter;
import org.example.questao2.constants.InvoiceType;

@Getter
public class Invoice {
    private final String clientName;
    private final String clientEmail;
    private final double amount;
    private final InvoiceType type;

    public Invoice(String clientName, String clientEmail, double amount, InvoiceType type) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.amount = amount;
        this.type = type;
    }

    public boolean hasValidEmail() {
        return clientEmail != null && clientEmail.contains("@");
    }

    public String generateInvoiceText() {
        return "--- NOTA FISCAL ---\n" +
                "Cliente: " + clientName + "\n" +
                "Valor: R$ " + amount + "\n" +
                "Tipo: " + type.getDescription() + "\n" +
                "---------------------";
    }
}