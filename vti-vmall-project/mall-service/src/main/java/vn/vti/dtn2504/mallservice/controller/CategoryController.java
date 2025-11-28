package vn.vti.dtn2504.mallservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.dtn2504.common.api.response.ApiResponse;
import vn.vti.dtn2504.mallservice.dto.request.CreateCategoryRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateCategoryResponse;
import vn.vti.dtn2504.mallservice.entity.Category;
import vn.vti.dtn2504.mallservice.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> createCategory(
            @RequestBody CreateCategoryRequest request) {
        CreateCategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success(category));
    }
}
