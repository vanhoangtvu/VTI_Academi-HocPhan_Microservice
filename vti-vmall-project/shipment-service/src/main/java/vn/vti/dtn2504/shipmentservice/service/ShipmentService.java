package vn.vti.dtn2504.shipmentservice.service;

import vn.vti.dtn2504.shipmentservice.dto.CreateShipmentRequest;
import vn.vti.dtn2504.shipmentservice.entity.Shipment;

public interface ShipmentService {
    Shipment createShipment(CreateShipmentRequest request);
}
