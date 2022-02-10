package com.example.finalapi.Service;

import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Category;
import com.example.finalapi.Entity.Movie;
import com.example.finalapi.Repository.CastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CastServiceImpl implements CastService {
    Path imagePath = Paths.get("images");
    @Autowired
    private CastRepo castRepo;

    @Override
    public Cast addCast(Cast cast, MultipartFile photo) {
        Cast cast1 = new Cast();
        cast1.setName(cast.getName());
        cast1.setDescription(cast.getDescription());
        cast1.setImage(imagePath.resolve(photo.getOriginalFilename()).toString());
        return castRepo.save(cast1);
    }

    @Override
    public Page<Cast> getAllCasts(Pageable pageable) {
        return castRepo.findAll(pageable);
    }

    @Override
    public Cast updateCast(int id, Cast cast, MultipartFile photo) {
        List<Cast> casts = castRepo.findAll();
        for (int i = 0 ; i < casts.size() ; i++)
        {
            Cast cast1 = casts.get(i);
            if(cast1.getId() == id)
            {
                cast1.setName(cast.getName());
                cast1.setDescription(cast.getDescription());
                cast1.setImage(imagePath.resolve(photo.getOriginalFilename()).toString());
                return castRepo.save(cast1);
            }
        }
        return null;
    }

    @Override
    public void deleteCast(int id) {
        castRepo.deleteById(id);
    }
}
