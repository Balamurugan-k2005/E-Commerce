package com.example.EComApp.service.category;

import com.example.EComApp.dto.request.categoryRequest.CategoryRequest;
import com.example.EComApp.dto.response.category.categoryResponse.CategoryResponse;
import com.example.EComApp.exception.BadRequestException;
import com.example.EComApp.exception.DuplicateResourceException;
import com.example.EComApp.exception.ResourceNotFoundException;
import com.example.EComApp.mapper.category.CategoryMapper;
import com.example.EComApp.model.category.Category;
import com.example.EComApp.repository.category.CategoryRepository;
import com.example.EComApp.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryRequest request){
        if(categoryRepository.existsByNameIgnoreCase(request.getName())){
            throw new RuntimeException("Category name is already exists");
        }

        Category category = categoryMapper.mapToCategory(request);
        category.setSlug(generateSlug(request.getName()));
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(savedCategory);
    }

    public Page<CategoryResponse> getAllCategories(Pageable pageable){
        Page<Category> categoryList = categoryRepository.findAll(pageable);
        return categoryList.map(categoryMapper::mapToCategoryResponse);
    }

    public CategoryResponse updateCategory(Long id,CategoryRequest request){
        Category category = categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("category not found with this id : " + id));

        if(!category.getName().equalsIgnoreCase(request.getName())
            && categoryRepository.existsByNameIgnoreCase(request.getName())){
            throw new DuplicateResourceException("category is already exists");
        }

        categoryMapper.updateEntity(request,category);
        category.setSlug(generateSlug(request.getName()));

        Category updated = categoryRepository.save(category);

        return categoryMapper.mapToCategoryResponse(updated);
    }

    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + id));

        if (!category.isActive()) {
            throw new BadRequestException("Category is already deleted.");
        }

        if (productRepository.existsByCategoryIdAndIsActiveTrue(id)) {
            throw new BadRequestException(
                    "Cannot delete category because it contains products.");
        }

        category.setActive(false);

        categoryRepository.save(category);
    }

    public String generateSlug(String input){
        return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]","")
                .replaceAll("\\s+", "-");
    }

}
