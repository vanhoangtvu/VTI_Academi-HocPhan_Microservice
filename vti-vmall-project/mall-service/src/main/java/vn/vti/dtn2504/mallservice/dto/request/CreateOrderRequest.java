package vn.vti.dtn2504.mallservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private List<OrderItemRequest> items;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemRequest {
        private Integer productId;
        private Integer quantity;
    }
}
