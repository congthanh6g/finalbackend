package com.example.finalapi.Repository;

import com.example.finalapi.Entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepo extends JpaRepository<Director , Integer> {
    Director findByName(String name);
}
