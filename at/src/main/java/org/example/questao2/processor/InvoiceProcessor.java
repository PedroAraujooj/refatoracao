package org.example.questao2.processor;

import org.example.questao2.model.Invoice;
import org.example.questao2.service.EmailService;

public class InvoiceProcessor {
    private final EmailService emailService = new EmailService();

    public void process(Invoice invoice) {
        if (!invoice.hasValidEmail()) {
            System.out.println("Email inválido. Falha no envio.");
            return;
        }

        String invoiceText = invoice.generateInvoiceText();
        System.out.println(invoiceText);
        emailService.send(invoice.getClientEmail(), invoiceText);
    }
}