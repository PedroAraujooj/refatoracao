package org.example.questao5.service;

import org.example.questao5.notification.NotificationChannel;

import java.util.ServiceLoader;

public class NotificationService {
    public void notifyAllUsers(String message) {
        ServiceLoader<NotificationChannel> loader =
                ServiceLoader.load(NotificationChannel.class);

        for (NotificationChannel channel : loader) {
            channel.notifyUser(message);
        }
    }
}