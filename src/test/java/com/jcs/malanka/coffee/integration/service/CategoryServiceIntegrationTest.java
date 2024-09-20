package com.jcs.malanka.coffee.integration.service;

import com.jcs.malanka.coffee.dto.CategoryDto;
import com.jcs.malanka.coffee.integration.AbstractIntegrationTest;
import com.jcs.malanka.coffee.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@DisplayName("Category Service Integration Tests")
public class CategoryServiceIntegrationTest extends AbstractIntegrationTest {
    private final CategoryService categoryService;

    @Nested
    @DisplayName("findAll() method tests")
    class FindAllTests {

        @Test
        @DisplayName("when find all the results successfully")
        void testFindAllWhenSuccessfulResult() {
            List<CategoryDto> categories = categoryService.findAll();

            assertThat(categories).isNotNull();
            assertThat(categories).hasSize(2);
        }

    }

    @Nested
    @DisplayName("findById(UUID) method tests")
    class FindByIdTests {

        @Test
        @DisplayName("when category exists")
        void testFindByIdWhenSuccessfulResult() {
            UUID uuid = UUID.fromString("3c91cc57-298a-4e88-9b40-a52d4720bb93");

            CategoryDto category = categoryService.findById(uuid);

            assertThat(category).isNotNull();
            assertThat(category.id()).isEqualTo(uuid);
            assertThat(category.name()).isEqualTo("coffee");
        }

        @Test
        @DisplayName("when category not found")
        void testFindByIdWhenNotFound() {
            assertThatThrownBy(() -> categoryService.findById(UUID.randomUUID()))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("Failed to find category by id: ");
        }
    }

    @Nested
    @DisplayName("create(CategoryDto) method tests")
    class CreateTests {

        @Test
        @DisplayName("when created successfully")
        void testCreateWhenCreatedSuccessfully() {
            CategoryDto newCategory = CategoryDto.builder()
                    .name("pizza")
                    .build();

            CategoryDto createdCategory = categoryService.create(newCategory);

            assertThat(createdCategory).isNotNull();
            assertThat(createdCategory.id()).isNotNull();
            assertThat(createdCategory.name()).isEqualTo(newCategory.name());
        }

    }

    @Nested
    @DisplayName("update(CategoryDto) method tests")
    class UpdateTests {

        @Test
        @DisplayName("when updated successfully")
        void testUpdateWhenUpdatedSuccessfully() {
            CategoryDto categoryForUpdate = CategoryDto.builder()
                    .id(UUID.fromString("3c91cc57-298a-4e88-9b40-a52d4720bb93"))
                    .name("pizza")
                    .build();

            CategoryDto updatedCategory = categoryService.update(categoryForUpdate);

            assertThat(updatedCategory).isNotNull();
            assertThat(updatedCategory.id()).isEqualTo(categoryForUpdate.id());
            assertThat(updatedCategory.name()).isEqualTo(categoryForUpdate.name());
        }

        @Test
        @DisplayName("when category not found")
        void testUpdateWhenCategoryNotFound() {
            CategoryDto notExistsCategory = CategoryDto.builder()
                    .id(UUID.randomUUID())
                    .name("pizza")
                    .build();

            assertThatThrownBy(() -> categoryService.update(notExistsCategory))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("Failed to update category by id: ");
        }

    }

    @Nested
    @DisplayName("delete(UUID) method tests")
    class DeleteTests {

        @Test
        @DisplayName("when deleted successfully")
        void testDeleteWhenDeletedSuccessfully() {
            assertThatNoException()
                    .isThrownBy(() -> categoryService.delete(UUID.fromString("3c91cc57-298a-4e88-9b40-a52d4720bb93")));
        }

        @Test
        @DisplayName("when category not found")
        void testDeleteWhenCategoryNotFound() {
            assertThatThrownBy(() -> categoryService.delete(UUID.randomUUID()))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("Failed to delete category by id: ");
        }

    }

}
