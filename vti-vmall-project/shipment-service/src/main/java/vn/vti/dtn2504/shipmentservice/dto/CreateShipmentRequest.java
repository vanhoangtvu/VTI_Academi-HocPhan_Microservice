package vn.vti.dtn2504.shipmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShipmentRequest {
    private Integer orderId;
    private String receiverName;
    private String address;
    private Integer quantity;
    private Integer totalAmount;
}
