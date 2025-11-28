package vn.vti.dtn2504.notificationservice.dto;

import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendNotificationRequest {
    private String message;
    private String sendTo;
    private String title;

}
