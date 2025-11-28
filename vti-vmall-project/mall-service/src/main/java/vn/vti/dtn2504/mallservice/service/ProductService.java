package vn.vti.dtn2504.mallservice.service;

import vn.vti.dtn2504.mallservice.dto.request.CreateProductRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateProductResponse;
import vn.vti.dtn2504.mallservice.entity.Product;

import java.util.List;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest request);
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    void updateProduct(Product product);
}
