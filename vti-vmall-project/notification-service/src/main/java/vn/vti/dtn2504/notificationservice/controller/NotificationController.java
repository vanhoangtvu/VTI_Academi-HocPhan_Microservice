package vn.vti.dtn2504.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vti.dtn2504.notificationservice.dto.SendNotificationRequest;
import vn.vti.dtn2504.notificationservice.service.SendNotificationService;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final SendNotificationService service;
    @PostMapping
    public ResponseEntity<?> sendNotification(@RequestBody SendNotificationRequest request) {
        service.sendNotification(request);
        return ResponseEntity.ok().build();
    }


}
