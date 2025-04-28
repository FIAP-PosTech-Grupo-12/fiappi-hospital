package br.com.fiap.notification.notificationListener;

import br.com.fiap.notification.config.RabbitMQConfig;
import br.com.fiap.notification.model.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void receiveNotification(NotificationMessage message) {
        log.info("Recebendo notificação para email: {}", message.getEmail());
        log.info("Evento: {} - Data: {}", message.getEvent(), message.getDate());
        log.info("Enviando e-mail para {}", message.getEmail());
    }

}
