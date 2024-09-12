package com.jcs.malanka.coffee.service;

import com.jcs.malanka.coffee.dto.CategoryDto;
import com.jcs.malanka.coffee.entity.CategoryEntity;
import com.jcs.malanka.coffee.mapper.CategoryMapper;
import com.jcs.malanka.coffee.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public CategoryDto findById(UUID id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find category by id: %s".formatted(id)));
    }

    @Transactional
    public CategoryDto create(CategoryDto dto) {
        CategoryEntity savedCategory = categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toDto(savedCategory);
    }

    @Transactional
    public CategoryDto update(CategoryDto dto) {
        CategoryEntity existingEntity = categoryRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Failed to update category by id: %s".formatted(dto.id())));
        categoryMapper.update(existingEntity, categoryMapper.toEntity(dto));
        return categoryMapper.toDto(existingEntity);
    }

    @Transactional
    public void delete(UUID id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Failed to delete category by id: %s".formatted(id)));
        categoryRepository.delete(entity);
    }

}
