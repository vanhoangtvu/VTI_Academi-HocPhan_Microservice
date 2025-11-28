package vn.vti.dtn2504.mallservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendNotificationRequest {
    private String sendTo;
    private String message;
    private String title;
}
