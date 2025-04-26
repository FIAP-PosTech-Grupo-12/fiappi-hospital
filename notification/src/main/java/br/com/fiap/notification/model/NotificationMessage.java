package br.com.fiap.notification.model;

import lombok.Data;

@Data

public class NotificationMessage {

    private String email;
    private String event;
    private String date;
}
