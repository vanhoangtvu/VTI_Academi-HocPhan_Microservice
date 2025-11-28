package vn.vti.dtn2504.mallservice.service;

import vn.vti.dtn2504.mallservice.dto.request.CreateCategoryRequest;
import vn.vti.dtn2504.mallservice.dto.response.CreateCategoryResponse;
import vn.vti.dtn2504.mallservice.entity.Category;

import java.util.List;

public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest request);
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
}
