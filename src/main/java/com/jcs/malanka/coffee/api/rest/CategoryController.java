package com.jcs.malanka.coffee.api.rest;

import com.jcs.malanka.coffee.dto.CategoryDto;
import com.jcs.malanka.coffee.dto.api.DataWrapper;
import com.jcs.malanka.coffee.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DataWrapper<List<CategoryDto>> getAllCategories() {
        return new DataWrapper<>(categoryService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DataWrapper<CategoryDto> findCategoryById(@PathVariable UUID id) {
        return new DataWrapper<>(categoryService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DataWrapper<CategoryDto> createCategory(@RequestBody CategoryDto dto) {
        return new DataWrapper<>(categoryService.create(dto));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public DataWrapper<CategoryDto> updateCategory(@RequestBody CategoryDto dto) {
        return new DataWrapper<>(categoryService.update(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
    }

}
