package vn.vti.dtn2504.mallservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vti.dtn2504.common.api.response.ApiResponse;
import vn.vti.dtn2504.mallservice.dto.request.CreateOrderRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateOrderResponse;
import vn.vti.dtn2504.mallservice.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<CreateOrderResponse>> createOrder(
            @RequestBody CreateOrderRequest request,
            Authentication authentication) {
        String username = authentication.getName();
        CreateOrderResponse response = orderService.createOrder(request, username);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
