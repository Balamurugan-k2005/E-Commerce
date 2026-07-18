package com.example.EComApp.mapper.category;

import com.example.EComApp.dto.request.categoryRequest.CategoryRequest;
import com.example.EComApp.dto.response.category.categoryResponse.CategoryResponse;
import com.example.EComApp.model.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse mapToCategoryResponse(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "productList", ignore = true)
    Category mapToCategory(CategoryRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "productList", ignore = true)
    void updateEntity(CategoryRequest request,
                      @MappingTarget Category category);
}
