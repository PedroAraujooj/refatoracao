package org.example.questao6;

import org.example.questao6.service.DocumentService;

public class Main {
    public static void main(String[] args) {
        DocumentService service = new DocumentService();
        service.printAll();
    }
}