package org.example.questao5.notification;

public class SmsNotification implements NotificationChannel {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
