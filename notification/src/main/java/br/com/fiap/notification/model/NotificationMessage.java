package br.com.fiap.notification.model;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class NotificationMessage {
    private String email;
    private String event;
    private ZonedDateTime date;
}
