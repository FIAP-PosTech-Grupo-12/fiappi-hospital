package br.com.fiap.notification.notificationListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.fiap.notification.model.NotificationMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationListener {

    @RabbitListener(queues = "notification-topic")
    public void receiveNotification(NotificationMessage message) {
        log.info("Recebendo notificação para email: {}", message.getEmail());
        log.info("Evento: {} - Data: {}", message.getEvent(), message.getDate());
        log.info("Enviando e-mail para {}", message.getEmail());

    }
}
