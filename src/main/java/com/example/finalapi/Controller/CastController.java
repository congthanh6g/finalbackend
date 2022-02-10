package com.example.finalapi.Controller;

import com.example.finalapi.BaseResponse.BaseResponse;
import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Movie;
import com.example.finalapi.Service.CastService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/cast")
public class CastController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private CastService castService;

    @GetMapping
    private ResponseEntity getAllCasts(@RequestParam(value = "page" , defaultValue = "0") int page,
                                       @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        Pageable paging = PageRequest.of(page , size);
        BaseResponse res = new BaseResponse();
        Page<Cast> pageTuts;
        pageTuts = castService.getAllCasts(paging);
        res.currentPage = pageTuts.getNumber();
        res.totalItems = (int) pageTuts.getTotalElements();
        res.totalPages = pageTuts.getTotalPages();
        res.datas = pageTuts.getContent();
        return ResponseEntity.ok(res);
    }

    @PostMapping
    private ResponseEntity createCast(@RequestParam MultipartFile image,
                                      String jsonObject) throws IOException
    {
        Cast cast = null;
        cast = new ObjectMapper().readValue(jsonObject , Cast.class);
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))){
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename());
        try(OutputStream os = Files.newOutputStream(file)){
            os.write(image.getBytes());
        }

        return ResponseEntity.ok(castService.addCast(cast , image));
    }

    @PutMapping("/{id}")
    private ResponseEntity updateCast(@PathVariable int id , @RequestParam MultipartFile image,
                                      String jsonObject ) throws IOException
    {
        Cast cast = null;
        cast = new ObjectMapper().readValue(jsonObject , Cast.class);
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))){
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename());
        try(OutputStream os = Files.newOutputStream(file)){
            os.write(image.getBytes());
        }

        return ResponseEntity.ok(castService.updateCast(id , cast , image));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteCast(@PathVariable int id)
    {
       castService.deleteCast(id);
        return ResponseEntity.ok("Delete thanh cong");
    }

}
