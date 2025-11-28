package vn.vti.dtn2504.shipmentservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vn.vti.dtn2504.shipmentservice.client.ApiResponse;
import vn.vti.dtn2504.shipmentservice.client.UserClient;
import vn.vti.dtn2504.shipmentservice.client.UserInfo;
import vn.vti.dtn2504.shipmentservice.dto.CreateShipmentRequest;
import vn.vti.dtn2504.shipmentservice.service.ShipmentService;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShipmentListener {
    
    private final ShipmentService shipmentService;
    private final UserClient userClient;
    
    @RabbitListener(queues = "${queue.shipment.queue}")
    public void handleCreateShipment(CreateShipmentRequest request) {
        log.info("Received shipment request: {}", request);
        try {
            ApiResponse<UserInfo> response = userClient.getUserInfo(request.getReceiverName());
            UserInfo userInfo = response.getData();
            if (userInfo != null) {
                request.setReceiverName(userInfo.getName() != null ? userInfo.getName() : request.getReceiverName());
                request.setAddress(userInfo.getAddress() != null ? userInfo.getAddress() : "No address");
            }
            
            shipmentService.createShipment(request);
            log.info("Shipment created successfully for order: {}", request.getOrderId());
        } catch (Exception e) {
            log.error("Failed to create shipment: {}", e.getMessage());
        }
    }
}
