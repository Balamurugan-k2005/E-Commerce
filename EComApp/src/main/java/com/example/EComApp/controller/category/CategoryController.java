package com.example.EComApp.controller.category;

import com.example.EComApp.dto.request.categoryRequest.CategoryRequest;
import com.example.EComApp.dto.response.category.categoryResponse.CategoryResponse;
import com.example.EComApp.model.base.ApiResponse;
import com.example.EComApp.service.category.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request){
        CategoryResponse response = categoryServiceImpl.createCategory(request);
        return new ResponseEntity<>(ApiResponse.success(response,"Category created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryResponse>>> getAllCategories(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",required = false, defaultValue = "5") Integer pageSize){
        Page<CategoryResponse> categoryResponseList = categoryServiceImpl.getAllCategories(PageRequest.of(pageNo - 1,pageSize));
        return new ResponseEntity<>(ApiResponse.success(categoryResponseList,"categories fetched successfully"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable("id") Long id,
                                                                        @Valid @RequestBody CategoryRequest request){
        CategoryResponse response = categoryServiceImpl.updateCategory(id, request);
        return new ResponseEntity<>(ApiResponse.success(response, "category updated successfully"), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable("id") Long id){
        categoryServiceImpl.deleteCategory(id);
        return new ResponseEntity<>(ApiResponse.success("Deleted category with id : " + id,"category deleted successfully"), HttpStatus.OK);
    }

}
