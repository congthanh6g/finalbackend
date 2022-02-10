package com.example.finalapi.Controller;

import com.example.finalapi.BaseResponse.BaseResponse;
import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Category;
import com.example.finalapi.Entity.Director;
import com.example.finalapi.Entity.Movie;
import com.example.finalapi.Repository.CastRepo;
import com.example.finalapi.Repository.CategoryRepo;
import com.example.finalapi.Repository.DirectorRepo;
import com.example.finalapi.Service.CategoryService;
import com.example.finalapi.Service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private MovieService movieService;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CastRepo castRepo;

    @Autowired
    private DirectorRepo directorRepo;
    @GetMapping
    private ResponseEntity getAllMovies(
                                        @RequestParam(value = "name" , required = false) String name,
                                        @RequestParam(value = "page" , defaultValue = "0") int page,
                                        @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        if(name == null) {
            Pageable paging = PageRequest.of(page , size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getAllMovies(paging);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
        else
        {
            Pageable paging = PageRequest.of(page , size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getMovieByTitle(paging , name);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
    }

    @PostMapping
    private ResponseEntity addMovie(@RequestParam MultipartFile image,
                                    String jsonObject) throws IOException
    {
        Movie movie = null;
        movie = new ObjectMapper().readValue(jsonObject , Movie.class);
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))){
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename());
        try(OutputStream os = Files.newOutputStream(file)){
            os.write(image.getBytes());
        }
        return ResponseEntity.ok(movieService.addMovie(movie , image));
    }

    @PutMapping("/{id}")
    private ResponseEntity updateMovie(@RequestParam MultipartFile image,
                                       String jsonObject , @PathVariable int id) throws IOException
    {
        Movie movie = null;
        movie = new ObjectMapper().readValue(jsonObject , Movie.class);
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))){
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename());
        try(OutputStream os = Files.newOutputStream(file)){
            os.write(image.getBytes());
        }

        return ResponseEntity.ok(movieService.updateMovie(id , movie , image));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteMovie(@PathVariable int id)
    {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Delete thanh cong");
    }

    @GetMapping("/category")
    private ResponseEntity getMovieByCategory(@RequestParam(value = "category" , required = false) String category,
                                              @RequestParam(value = "page" , defaultValue = "0") int page,
                                              @RequestParam(value = "size" , defaultValue = "3") int size)

    {
        if(category == null)
        {
            Pageable paging = PageRequest.of(page , size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getAllMovies(paging);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
        else {
            Category category1 = categoryRepo.findByName(category);
            Pageable paging = PageRequest.of(page, size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getMovieByCategory(paging, category1);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/director")
    private ResponseEntity getMovieByDirector(@RequestParam(value = "director" , required = false) String director,
                                              @RequestParam(value = "page" , defaultValue = "0") int page,
                                              @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        if(director == null)
        {
            Pageable paging = PageRequest.of(page , size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getAllMovies(paging);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
        else {
            Director director1 = directorRepo.findByName(director);
            Pageable paging = PageRequest.of(page, size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getMovieByDirector(paging, director1);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/cast")
    private ResponseEntity getMovieByCast(@RequestParam(value = "cast" , required = false) String cast,
                                          @RequestParam(value = "page" , defaultValue = "0") int page,
                                          @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        if(cast == null)
        {
            Pageable paging = PageRequest.of(page , size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getAllMovies(paging);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
        else {
            Cast cast1 = castRepo.findByName(cast);
            Pageable paging = PageRequest.of(page, size);
            BaseResponse res = new BaseResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getMovieByCast(paging, cast1);
            res.currentPage = pageTuts.getNumber();
            res.totalItems = (int) pageTuts.getTotalElements();
            res.totalPages = pageTuts.getTotalPages();
            res.datas = pageTuts.getContent();
            return ResponseEntity.ok(res);
        }
    }

}
