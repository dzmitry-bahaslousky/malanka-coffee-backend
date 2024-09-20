package com.jcs.malanka.coffee.service;

import com.jcs.malanka.coffee.dto.CategoryDto;
import com.jcs.malanka.coffee.entity.CategoryEntity;
import com.jcs.malanka.coffee.mapper.CategoryMapper;
import com.jcs.malanka.coffee.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Category Service Tests")
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Nested
    @DisplayName("findAll() method tests")
    class FindAllTest {

        @Test
        @DisplayName("when several results found")
        void testFindAllWhenSeveralResultsFound() {
            List<CategoryEntity> entities = Stream.generate(CategoryEntity::new)
                    .limit(10)
                    .toList();
            when(categoryRepository.findAll()).thenReturn(entities);
            when(categoryMapper.toDto(any(CategoryEntity.class))).thenReturn(mock(CategoryDto.class));

            List<CategoryDto> result = categoryService.findAll();

            assertThat(result).isNotNull();
            assertThat(result).hasSize(entities.size());
            verify(categoryMapper, times(entities.size())).toDto(any(CategoryEntity.class));
        }

        @Test
        @DisplayName("when no result found")
        void testFindAllWhenNoResultFound() {
            when(categoryRepository.findAll()).thenReturn(List.of());

            List<CategoryDto> result = categoryService.findAll();

            assertThat(result).isNotNull();
            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("findById(UUID) method tests")
    class FindByIdTest {

        @Test
        @DisplayName("when category exists")
        void testFindByIdWhenCategoryExists() {
            when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.of(mock(CategoryEntity.class)));
            when(categoryMapper.toDto(any(CategoryEntity.class))).thenReturn(mock(CategoryDto.class));

            CategoryDto result = categoryService.findById(UUID.randomUUID());

            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("when category not found expect exception")
        void testFindByIdWhenCategoryNotFoundExpectException() {
            when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
            UUID uuid = UUID.randomUUID();

            assertThatThrownBy(() -> categoryService.findById(uuid))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Failed to find category by id: %s", uuid);
        }

    }

    @Nested
    @DisplayName("create(CategoryDto) method tests")
    class CreateTest {

        @Test
        @DisplayName("when successfully created")
        void testCreateWhenSuccessfullyCreated() {
            when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(mock(CategoryEntity.class));
            when(categoryMapper.toDto(any(CategoryEntity.class))).thenReturn(mock(CategoryDto.class));
            when(categoryMapper.toEntity(any(CategoryDto.class))).thenReturn(mock(CategoryEntity.class));

            CategoryDto created = categoryService.create(mock(CategoryDto.class));

            assertThat(created).isNotNull();
        }

    }

    @Nested
    @DisplayName("update(CategoryDto) method tests")
    class UpdateTest {

        @Test
        @DisplayName("when successfully updated")
        void testUpdateWhenSuccessfullyUpdated() {
            when(categoryRepository.findById(any())).thenReturn(Optional.of(mock(CategoryEntity.class)));
            doNothing().when(categoryMapper).update(any(CategoryEntity.class), any(CategoryEntity.class));
            when(categoryMapper.toEntity(any(CategoryDto.class))).thenReturn(mock(CategoryEntity.class));
            when(categoryMapper.toDto(any(CategoryEntity.class))).thenReturn(mock(CategoryDto.class));

            CategoryDto updated = categoryService.update(mock(CategoryDto.class));

            assertThat(updated).isNotNull();
        }

        @Test
        @DisplayName("when updated category not found expect exception")
        void testUpdateWhenCategoryNotFoundExpectException() {
            when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
            CategoryDto mockedCategory = mock(CategoryDto.class);
            when(mockedCategory.id()).thenReturn(UUID.randomUUID());

            assertThatThrownBy(() -> categoryService.update(mockedCategory))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Failed to update category by id: %s", mockedCategory.id());
        }

    }

    @Nested
    @DisplayName("delete(UUID) method tests")
    class DeleteTest {

        @Test
        @DisplayName("when successfully deleted")
        void testDeleteWhenSuccessfullyDeleted() {
            when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.of(mock(CategoryEntity.class)));
            doNothing().when(categoryRepository).delete(any(CategoryEntity.class));

            assertThatNoException().isThrownBy(() -> categoryService.delete(UUID.randomUUID()));
        }

        @Test
        @DisplayName("when deleted category not found")
        void testDeleteWhenCategoryNotFoundExpectException() {
            when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
            UUID uuid = UUID.randomUUID();

            assertThatThrownBy(() -> categoryService.delete(uuid))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Failed to delete category by id: %s", uuid);
        }

    }

}