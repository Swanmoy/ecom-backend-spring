package com.swanmoy.ecom.services.admin.category;

import com.swanmoy.ecom.dto.CategoryDto;
import com.swanmoy.ecom.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
}
