package com.example.finalapi.Service;

import com.example.finalapi.Entity.Category;
import com.example.finalapi.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        List<Category> lists = categoryRepo.findAll();
        for (int i = 0 ; i < lists.size() ; i++)
        {
            Category category1 = lists.get(i);
            if (category1.getId() == id)
            {
                category1.setName(category.getName());
                categoryRepo.save(category1);
                return category1;
            }
        }
        return null;
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }
}
