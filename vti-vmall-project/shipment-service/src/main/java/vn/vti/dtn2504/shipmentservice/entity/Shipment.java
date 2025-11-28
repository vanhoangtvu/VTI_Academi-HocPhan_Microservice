package vn.vti.dtn2504.shipmentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer orderId;
    private String receiverName;
    private String address;
    private Integer quantity;
    private Integer totalAmount;
    private String status; // PENDING, SHIPPING, DELIVERED
    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
