package com.example.finalapi.Service;

import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Category;
import com.example.finalapi.Entity.Director;
import com.example.finalapi.Entity.Movie;
import com.example.finalapi.Repository.CastRepo;
import com.example.finalapi.Repository.CategoryRepo;
import com.example.finalapi.Repository.DirectorRepo;
import com.example.finalapi.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    Path imagePath = Paths.get("images");
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CastRepo castRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public Movie addMovie(Movie movie , MultipartFile photo) {
        Movie movie1 = new Movie();
        movie1.setName(movie.getName());
        movie1.setView(movie.getView());
        movie1.setDescription(movie.getDescription());
        movie1.setDuration(movie.getDuration());
        movie1.setImage(imagePath.resolve(photo.getOriginalFilename()).toString());
        movie1.getCategories().addAll(movie.getCategories().stream()
                .map(m -> {
                    Category category = categoryRepo.findByName(m.getName());
                    category.getMovies().add(movie1);
                    return category;
                }).collect(Collectors.toList()));
        movie1.getCasts().addAll(movie.getCasts().stream()
                .map(c -> {
                    Cast cast = castRepo.findByName(c.getName());
                    cast.getMovies().add(movie1);
                    return cast;
                }).collect(Collectors.toList()));
        movie1.getDirectors().addAll(movie.getDirectors().stream()
                .map(d -> {
                    Director director = directorRepo.findByName(d.getName());
                    director.getMovies().add(movie1);
                    return director;
                }).collect(Collectors.toList()));
        return movieRepo.save(movie1);
    }

    @Override
    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepo.findAll(pageable);
    }

    @Override
    public Movie updateMovie(int id, Movie movie , MultipartFile photo) {
        List<Movie> movies = movieRepo.findAll();
        for(int i = 0 ; i < movies.size() ; i++)
        {
            Movie movie1 = movies.get(i);
            if(movie1.getId() == id)
            {
                movie1.setName(movie.getName());
                movie1.setView(movie.getView());
                movie1.setDescription(movie.getDescription());
                movie1.setDuration(movie.getDuration());
                movie1.setImage(imagePath.resolve(photo.getOriginalFilename()).toString());
                movie1.getCategories().addAll(movie.getCategories().stream()
                        .map(m -> {
                            Category category = categoryRepo.findByName(m.getName());
                            category.getMovies().add(movie1);
                            return category;
                        }).collect(Collectors.toList()));
                movie1.getCasts().addAll(movie.getCasts().stream()
                        .map(c -> {
                            Cast cast = castRepo.findByName(c.getName());
                            cast.getMovies().add(movie1);
                            return cast;
                        }).collect(Collectors.toList()));
                movie1.getDirectors().addAll(movie.getDirectors().stream()
                        .map(d -> {
                            Director director = directorRepo.findByName(d.getName());
                            director.getMovies().add(movie1);
                            return director;
                        }).collect(Collectors.toList()));
                movieRepo.save(movie1);
                return movie1;
            }
        }
        return null;
    }

    @Override
    public void deleteMovie(int id) {
        Optional<Movie> movie = movieRepo.findById(id);
        if(movie.isPresent())
        {
            movie.get().removeCategory();
            movie.get().removeCast();
            movie.get().removeDirector();
            movieRepo.deleteById(movie.get().getId());
        }
    }

    @Override
    public Page<Movie> getMovieByCategory(Pageable pageable, Category category) {
        return movieRepo.findByCategories(pageable , category);
    }

    @Override
    public Page<Movie> getMovieByTitle(Pageable pageable, String title) {
        return movieRepo.findByNameContaining(pageable , title);
    }

    @Override
    public Page<Movie> getMovieByCast(Pageable pageable, Cast cast) {
        return movieRepo.findByCasts(pageable , cast);
    }

    @Override
    public Page<Movie> getMovieByDirector(Pageable pageable, Director director) {
        return movieRepo.findByDirectors(pageable , director);
    }


}
