package com.example.finalapi.Repository;

import com.example.finalapi.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category , Integer> {
    Category findByName(String name);
}
