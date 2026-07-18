package com.example.EComApp.dto.request.categoryRequest;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;

    private String description;

}
