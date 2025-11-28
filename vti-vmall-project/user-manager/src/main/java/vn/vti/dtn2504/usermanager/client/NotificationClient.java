package vn.vti.dtn2504.usermanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.vti.dtn2504.usermanager.client.payload.request.SendNotificationRequest;

@FeignClient(name = "notification-service")
public interface NotificationClient {
    @PostMapping("/api/v1/notifications")
    ResponseEntity<String> sendNotification(@RequestBody SendNotificationRequest request);
}
