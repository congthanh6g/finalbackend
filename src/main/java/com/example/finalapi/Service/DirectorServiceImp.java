package com.example.finalapi.Service;

import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Director;
import com.example.finalapi.Repository.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class DirectorServiceImp implements DirectorService {
    Path imagePath = Paths.get("images");
    @Autowired
    private DirectorRepo directorRepo;
    @Override
    public Director addDirector(Director director, MultipartFile file) {
        Director director1 = new Director();
        director1.setName(director.getName());
        director1.setDescription(director.getDescription());
        director1.setImage(imagePath.resolve(file.getOriginalFilename()).toString());
        return directorRepo.save(director1);
    }

    @Override
    public Page<Director> getAllDirectors(Pageable pageable) {
        return directorRepo.findAll(pageable);
    }

    @Override
    public Director updateDirector(int id, Director director, MultipartFile file) {
        List<Director> directors = directorRepo.findAll();
        for (int i = 0 ; i < directors.size() ; i++)
        {
            Director director1 = directors.get(i);
            if(director1.getId() == id)
            {
                director1.setName(director.getName());
                director1.setDescription(director.getDescription());
                director1.setImage(imagePath.resolve(file.getOriginalFilename()).toString());
                return directorRepo.save(director1);
            }
        }
        return null;
    }

    @Override
    public void deleteDirector(int id) {
        directorRepo.deleteById(id);
    }
}
