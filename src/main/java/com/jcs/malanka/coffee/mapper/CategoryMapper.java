package com.jcs.malanka.coffee.mapper;

import com.jcs.malanka.coffee.dto.CategoryDto;
import com.jcs.malanka.coffee.entity.CategoryEntity;

public interface CategoryMapper {

    CategoryDto toDto(CategoryEntity entity);

    CategoryEntity toEntity(CategoryDto dto);

    void update(CategoryEntity target, CategoryEntity source);

}
