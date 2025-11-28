package vn.vti.dtn2504.mallservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.vti.dtn2504.mallservice.dto.request.CreateProductRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateProductResponse;
import vn.vti.dtn2504.mallservice.entity.Product;
import vn.vti.dtn2504.mallservice.repository.ProductRepository;
import vn.vti.dtn2504.mallservice.service.ProductService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        log.info("Creating product: {}", request.getProductName());
        
        Product product = new Product();
        product.setName(request.getProductName());
        product.setPrice(request.getPrice() != null ? request.getPrice() : 100);
        product.setStock(request.getStock() != null ? request.getStock() : 50);
        product.setCategoryId(request.getCategoryId());
        
        Product savedProduct = productRepository.save(product);
        log.info("Product created with ID: {}", savedProduct.getId());
        
        CreateProductResponse response = new CreateProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setPrice(savedProduct.getPrice());
        response.setStock(savedProduct.getStock());
        response.setCategoryId(savedProduct.getCategoryId());
        
        return response;
    }
    
    @Override
    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }
    
    @Override
    public Product getProductById(Integer id) {
        log.info("Getting product by ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    
    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
