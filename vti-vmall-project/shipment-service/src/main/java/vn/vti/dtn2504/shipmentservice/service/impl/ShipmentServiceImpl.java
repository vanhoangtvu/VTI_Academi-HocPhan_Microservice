package vn.vti.dtn2504.shipmentservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.vti.dtn2504.shipmentservice.dto.CreateShipmentRequest;
import vn.vti.dtn2504.shipmentservice.entity.Shipment;
import vn.vti.dtn2504.shipmentservice.repository.ShipmentRepository;
import vn.vti.dtn2504.shipmentservice.service.ShipmentService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    
    private final ShipmentRepository shipmentRepository;
    
    @Override
    public Shipment createShipment(CreateShipmentRequest request) {
        log.info("Creating shipment for order: {}", request.getOrderId());
        
        Shipment shipment = new Shipment();
        shipment.setOrderId(request.getOrderId());
        shipment.setReceiverName(request.getReceiverName());
        shipment.setAddress(request.getAddress());
        shipment.setQuantity(request.getQuantity());
        shipment.setTotalAmount(request.getTotalAmount());
        shipment.setStatus("PENDING");
        
        Shipment saved = shipmentRepository.save(shipment);
        log.info("Shipment created with ID: {}, Receiver: {}, Quantity: {}, Amount: {}", 
                saved.getId(), saved.getReceiverName(), saved.getQuantity(), saved.getTotalAmount());
        
        return saved;
    }
}
