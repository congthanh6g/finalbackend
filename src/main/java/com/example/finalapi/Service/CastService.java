package com.example.finalapi.Service;

import com.example.finalapi.Entity.Cast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CastService {
    Cast addCast(Cast cast , MultipartFile photo);
    Page<Cast> getAllCasts(Pageable pageable);
    Cast updateCast(int id , Cast cast , MultipartFile photo);
    void deleteCast(int id);
}
