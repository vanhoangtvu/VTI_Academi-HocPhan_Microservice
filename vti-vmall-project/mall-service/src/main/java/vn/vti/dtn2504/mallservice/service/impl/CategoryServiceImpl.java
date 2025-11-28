package vn.vti.dtn2504.mallservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.vti.dtn2504.mallservice.dto.request.CreateCategoryRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateCategoryResponse;
import vn.vti.dtn2504.mallservice.entity.Category;
import vn.vti.dtn2504.mallservice.repository.CategoryRepository;
import vn.vti.dtn2504.mallservice.service.CategoryService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        log.info("Creating category: {}", request.getName());
        
        Category category = new Category();
        category.setName(request.getName());
        
        Category savedCategory = categoryRepository.save(category);
        log.info("Category created with ID: {}", savedCategory.getId());
        
        CreateCategoryResponse response = new CreateCategoryResponse();
        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());
        
        return response;
    }
    
    @Override
    public List<Category> getAllCategories() {
        log.info("Getting all categories");
        return categoryRepository.findAll();
    }
    
    @Override
    public Category getCategoryById(Integer id) {
        log.info("Getting category by ID: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }
}
