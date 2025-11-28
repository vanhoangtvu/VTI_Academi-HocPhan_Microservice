package vn.vti.dtn2504.notificationservice.service;

import vn.vti.dtn2504.notificationservice.dto.SendNotificationRequest;

public interface SendNotificationService {
    void sendNotification(SendNotificationRequest request);
}
