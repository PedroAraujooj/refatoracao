package org.example.questao2.service;

public class EmailService {
    public void send(String email, String content) {
        System.out.println("Enviando email para: " + email);
        System.out.println("Conteúdo:\n" + content);
    }
}