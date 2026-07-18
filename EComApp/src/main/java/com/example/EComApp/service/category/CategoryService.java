package com.example.EComApp.service.category;

import com.example.EComApp.dto.request.categoryRequest.CategoryRequest;
import com.example.EComApp.dto.response.category.categoryResponse.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    Page<CategoryResponse> getAllCategories(Pageable pageable);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);

}
