package vn.vti.dtn2504.mallservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.dtn2504.common.api.response.ApiResponse;
import vn.vti.dtn2504.mallservice.dto.request.CreateProductRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateProductResponse;
import vn.vti.dtn2504.mallservice.entity.Product;
import vn.vti.dtn2504.mallservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ApiResponse<CreateProductResponse>> createProduct(
      @RequestBody CreateProductRequest request) {
    CreateProductResponse response = productService.createProduct(request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(ApiResponse.success(products));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer id) {
    Product product = productService.getProductById(id);
    return ResponseEntity.ok(ApiResponse.success(product));
  }
}
