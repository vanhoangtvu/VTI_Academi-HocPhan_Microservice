package vn.vti.dtn2504.notificationservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vn.vti.dtn2504.notificationservice.dto.SendNotificationRequest;

import javax.management.Notification;

@Component@Slf4j
public class NotificationListener {
    @RabbitListener(queues = "${queue.notification.queue}")
    public void receiveNotification(SendNotificationRequest request) {
        log.info("(receiveNotification)Received notification: {}", request);
    }
}
