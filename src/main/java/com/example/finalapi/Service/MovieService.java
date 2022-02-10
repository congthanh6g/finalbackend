package com.example.finalapi.Service;

import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Category;
import com.example.finalapi.Entity.Director;
import com.example.finalapi.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie , MultipartFile photo);
    Page<Movie> getAllMovies(Pageable pageable);
    Movie updateMovie(int id , Movie movie , MultipartFile photo);
    void deleteMovie(int id);
    Page<Movie> getMovieByCategory(Pageable pageable , Category category);
    Page<Movie> getMovieByTitle(Pageable pageable , String title);
    Page<Movie> getMovieByCast(Pageable pageable , Cast cast);
    Page<Movie> getMovieByDirector(Pageable pageable , Director director);
}
