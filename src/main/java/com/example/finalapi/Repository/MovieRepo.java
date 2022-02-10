package com.example.finalapi.Repository;

import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Category;
import com.example.finalapi.Entity.Director;
import com.example.finalapi.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    Page<Movie> findByCategories(Pageable pageable , Category category);
    Page<Movie> findByNameContaining(Pageable pageable , String name);
    Page<Movie> findByCasts(Pageable pageable , Cast cast);
    Page<Movie> findByDirectors(Pageable pageable , Director director);
}
