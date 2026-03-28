package org.example.questao5.notification;

public class PushNotification implements NotificationChannel {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}