package vn.vti.dtn2504.mallservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private Integer orderId;
    private String username;
    private Integer totalAmount;
}
