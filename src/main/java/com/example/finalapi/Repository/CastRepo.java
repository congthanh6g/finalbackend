package com.example.finalapi.Repository;

import com.example.finalapi.Entity.Cast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepo extends JpaRepository<Cast , Integer> {
    Cast findByName(String cast);
}
