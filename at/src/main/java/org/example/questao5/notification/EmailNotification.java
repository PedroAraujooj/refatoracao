package org.example.questao5.notification;

public class EmailNotification implements NotificationChannel {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}