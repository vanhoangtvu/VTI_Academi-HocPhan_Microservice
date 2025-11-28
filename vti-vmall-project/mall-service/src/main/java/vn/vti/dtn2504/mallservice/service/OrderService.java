package vn.vti.dtn2504.mallservice.service;

import vn.vti.dtn2504.mallservice.dto.request.CreateOrderRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateOrderResponse;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest request, String username);
}
