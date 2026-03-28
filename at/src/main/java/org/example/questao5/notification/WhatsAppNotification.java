package org.example.questao5.notification;

public class WhatsAppNotification implements NotificationChannel {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending WHATSAPP: " + message);
    }
}