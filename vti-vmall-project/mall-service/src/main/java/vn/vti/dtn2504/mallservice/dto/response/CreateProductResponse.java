package vn.vti.dtn2504.mallservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
    private Integer id;
    private String name;
    private Integer price;
    private Integer stock;
    private Integer categoryId;
}
