package org.example.questao2.constants;

public enum InvoiceType {
    SIMPLE("Simples"),
    TAXED("Com imposto"),
    UNKNOWN("Desconhecido");

    private final String description;

    InvoiceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
