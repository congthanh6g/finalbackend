package com.example.finalapi.Service;

import com.example.finalapi.Entity.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DirectorService {
    Director addDirector(Director director , MultipartFile file);
    Page<Director> getAllDirectors(Pageable pageable);
    Director updateDirector(int id , Director director , MultipartFile file);
    void deleteDirector(int id);
}
