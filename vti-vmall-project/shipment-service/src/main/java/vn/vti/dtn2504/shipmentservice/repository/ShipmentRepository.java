package vn.vti.dtn2504.shipmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vti.dtn2504.shipmentservice.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
}
