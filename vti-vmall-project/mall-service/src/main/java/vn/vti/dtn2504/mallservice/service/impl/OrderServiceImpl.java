package vn.vti.dtn2504.mallservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vti.dtn2504.mallservice.client.UserClient;
import vn.vti.dtn2504.mallservice.dto.notification.SendNotificationRequest;
import vn.vti.dtn2504.mallservice.dto.request.CreateOrderRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateOrderResponse;
import vn.vti.dtn2504.mallservice.dto.shipment.CreateShipmentRequest;
import vn.vti.dtn2504.mallservice.entity.Order;
import vn.vti.dtn2504.mallservice.entity.OrderItem;
import vn.vti.dtn2504.mallservice.entity.Product;
import vn.vti.dtn2504.mallservice.repository.OrderItemRepository;
import vn.vti.dtn2504.mallservice.repository.OrderRepository;
import vn.vti.dtn2504.mallservice.service.OrderService;
import vn.vti.dtn2504.mallservice.service.ProductService;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;
    private final UserClient userClient;
    
    @Value("${queue.notification.routingKey}")
    private String routingKey;
    @Value("${queue.notification.queue}")
    private String queueName;
    @Value("${queue.notification.exchange}")
    private String exchangeName;
    @Value("${queue.shipment.routingKey}")
    private String shipmentRoutingKey;
    @Value("${queue.shipment.exchange}")
    private String shipmentExchange;
    
    @Override
    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request, String username) {
        log.info("Creating order for user: {}", username);
        Order order = new Order();
        order.setUsername(username);
        order.setTotalAmount(0);

        Order savedOrder = orderRepository.save(order);
        Integer totalAmount = 0;
        
        for (CreateOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            Product product = productService.getProductById(itemRequest.getProductId());
            
            if (product.getStock() < itemRequest.getQuantity()) {
                throw new RuntimeException("ko du sl: " + product.getName());
            }
            

            product.setStock(product.getStock() - itemRequest.getQuantity());
            productService.updateProduct(product);
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItemRepository.save(orderItem);
            
            Integer itemTotal = product.getPrice() * itemRequest.getQuantity();
            totalAmount = totalAmount + itemTotal;
        }
        
        savedOrder.setTotalAmount(totalAmount);
        orderRepository.save(savedOrder);

        try {
            SendNotificationRequest notification = new SendNotificationRequest();
            notification.setMessage("Don hang #" + savedOrder.getId() + " da duoc tao thanh cong! Tong tien: " + totalAmount + " VND");
            notification.setTitle("Xac nhan don hang");
            notification.setSendTo("send success to " + username);

            rabbitTemplate.convertAndSend(exchangeName, routingKey, notification);
            log.info("send notification done: {}", notification.getSendTo());
        } catch (Exception e) {
            log.error("Failed send notification: {}", e.getMessage());
        }
        
        try {
            Integer totalQuantity = 0;
            for (CreateOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
                totalQuantity += itemRequest.getQuantity();
            }
            CreateShipmentRequest shipmentRequest = new CreateShipmentRequest();
            shipmentRequest.setOrderId(savedOrder.getId());
            shipmentRequest.setReceiverName(username);
            shipmentRequest.setAddress(null);
            shipmentRequest.setQuantity(totalQuantity);
            shipmentRequest.setTotalAmount(totalAmount);
            
            rabbitTemplate.convertAndSend(shipmentExchange, shipmentRoutingKey, shipmentRequest);
            log.info("Shipment order: {}, by username: {}, quantity: {}, amount: {}", 
                    savedOrder.getId(), username, totalQuantity, totalAmount);
        } catch (Exception e) {
            log.error("Failed send shipment request: {}", e.getMessage());
        }


        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setUsername(savedOrder.getUsername());
        response.setTotalAmount(savedOrder.getTotalAmount());
        
        return response;
    }
}
