package com.jcs.malanka.coffee.mapper;

import com.jcs.malanka.coffee.dto.CategoryDto;
import com.jcs.malanka.coffee.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
final class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(CategoryEntity entity) {
        return new CategoryDto(entity.getId(), entity.getName());
    }

    @Override
    public CategoryEntity toEntity(CategoryDto dto) {
        return CategoryEntity.builder()
                .id(dto.id())
                .name(dto.name())
                .build();
    }

    @Override
    public void update(CategoryEntity target, CategoryEntity source) {
        target.setId(source.getId());
        target.setName(source.getName());
    }

}
