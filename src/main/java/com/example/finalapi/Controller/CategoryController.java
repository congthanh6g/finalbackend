package com.example.finalapi.Controller;

import com.example.finalapi.BaseResponse.BaseResponse;
import com.example.finalapi.Entity.Cast;
import com.example.finalapi.Entity.Category;
import com.example.finalapi.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    private ResponseEntity getAllCategories(@RequestParam(value = "page" , defaultValue = "0") int page,
                                            @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        Pageable paging = PageRequest.of(page , size);
        BaseResponse res = new BaseResponse();
        Page<Category> pageTuts;
        pageTuts = categoryService.getAllCategories(paging);
        res.currentPage = pageTuts.getNumber();
        res.totalItems = (int) pageTuts.getTotalElements();
        res.totalPages = pageTuts.getTotalPages();
        res.datas = pageTuts.getContent();
        return ResponseEntity.ok(res);
    }

    @PostMapping
    private ResponseEntity addCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/{id}")
    private ResponseEntity updateCategory(@PathVariable int id ,@RequestBody Category category)
    {
        return ResponseEntity.ok(categoryService.updateCategory(id , category));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteMapping(@PathVariable  int id)
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete");
    }
}
