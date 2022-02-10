package com.example.finalapi.Service;

import com.example.finalapi.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<Category> getAllCategories(Pageable pageable);
    Category addCategory(Category category);
    Category updateCategory(int id , Category category);
    void deleteCategory(int id);
}
