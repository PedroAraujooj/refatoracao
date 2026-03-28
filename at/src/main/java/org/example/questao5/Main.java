package org.example.questao5;

import org.example.questao5.service.NotificationService;

public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.notifyAllUsers("Bem-vindo!");
    }
}