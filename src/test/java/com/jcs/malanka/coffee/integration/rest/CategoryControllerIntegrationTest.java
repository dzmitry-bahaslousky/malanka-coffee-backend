package com.jcs.malanka.coffee.integration.rest;

import com.jcs.malanka.coffee.integration.AbstractIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@DisplayName("Category REST Controller Integration Tests")
class CategoryControllerIntegrationTest extends AbstractIntegrationTest {
    private final MockMvc mockMvc;

    @Nested
    @DisplayName("GET /api/v1/categories tests")
    class GetAll {

        @Test
        @DisplayName("when get all categories")
        void getAllCategories() throws Exception {
            mockMvc.perform(get("/api/v1/categories"))
                    .andDo(print())
                    .andExpectAll(
                            status().isOk(),
                            jsonPath("$.data").isArray(),
                            jsonPath("$.data.length()").value(2),
                            jsonPath("$.data[0].id").value("3c91cc57-298a-4e88-9b40-a52d4720bb93"),
                            jsonPath("$.data[0].name").value("coffee"),
                            jsonPath("$.data[1].id").value("3c91cc57-298a-4e88-9b40-a52d4720bb92"),
                            jsonPath("$.data[1].name").value("tea")
                    );
        }

    }

    @Nested
    @DisplayName("GET /api/v1/categories/:id tests")
    class GetById {

        @Test
        @DisplayName("when find the category")
        void findCategoryByIdWhenFound() throws Exception {
            mockMvc.perform(get("/api/v1/categories/{id}", "3c91cc57-298a-4e88-9b40-a52d4720bb93"))
                    .andDo(print())
                    .andExpectAll(
                            status().isOk(),
                            jsonPath("$.data").exists(),
                            jsonPath("$.data.id").value("3c91cc57-298a-4e88-9b40-a52d4720bb93"),
                            jsonPath("$.data.name").value("coffee")
                    );
        }

        @Test
        @DisplayName("when the category doesn't exists")
        void findCategoryByIdWhenNotFound() throws Exception {
            mockMvc.perform(get("/api/v1/categories/{id}", "3c91cc57-298a-4e88-9b40-a52d4720bb43"))
                    .andDo(print())
                    .andExpectAll(
                            status().isNotFound(),
                            jsonPath("$.type").value("about:blank"),
                            jsonPath("$.title").value("Not Found"),
                            jsonPath("$.status").value("404"),
                            jsonPath("$.detail").value("Failed to find category by id: 3c91cc57-298a-4e88-9b40-a52d4720bb43"),
                            jsonPath("$.instance").value("/api/v1/categories/3c91cc57-298a-4e88-9b40-a52d4720bb43")
                    );
        }

    }

    @Nested
    @DisplayName("POST /api/v1/categories tests")
    class CreateCategory {

        @Test
        @DisplayName("when correct body")
        void createCategoryWhenCorrectBody() throws Exception {
            mockMvc.perform(post("/api/v1/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "name": "test-name"
                                    }
                                    """))
                    .andDo(print())
                    .andExpectAll(
                            status().isCreated(),
                            jsonPath("$.data").exists(),
                            jsonPath("$.data.id").isNotEmpty(),
                            jsonPath("$.data.name").value("test-name")
                    );
        }

        @Test
        @DisplayName("when incorrect body")
        void createCategoryWhenIncorrectBody() throws Exception {
            mockMvc.perform(post("/api/v1/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "name": ""
                                    }
                                    """))
                    .andDo(print())
                    .andExpectAll(
                            status().isBadRequest(),
                            jsonPath("$.type").value("about:blank"),
                            jsonPath("$.title").value("Bad Request"),
                            jsonPath("$.status").value("400"),
                            jsonPath("$.detail").value("name: must not be empty"),
                            jsonPath("$.instance").value("/api/v1/categories")
                    );
        }

    }

    @Nested
    @DisplayName("PUT /api/v1/categories tests")
    class UpdateCategory {

        @Test
        @DisplayName("when correct body")
        void updateCategoryWhenCorrectBody() throws Exception {
            mockMvc.perform(put("/api/v1/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "id": "3c91cc57-298a-4e88-9b40-a52d4720bb93",
                                      "name": "coffee-new-name"
                                    }
                                    """))
                    .andDo(print())
                    .andExpectAll(
                            status().isOk(),
                            jsonPath("$.data").exists(),
                            jsonPath("$.data.id").value("3c91cc57-298a-4e88-9b40-a52d4720bb93"),
                            jsonPath("$.data.name").value("coffee-new-name")
                    );
        }

        @Test
        @DisplayName("when incorrect body")
        void updateCategoryWhenIncorrectBodyNoId() throws Exception {
            mockMvc.perform(put("/api/v1/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "name": ""
                                    }
                                    """))
                    .andDo(print())
                    .andExpectAll(
                            status().isBadRequest(),
                            jsonPath("$.type").value("about:blank"),
                            jsonPath("$.title").value("Bad Request"),
                            jsonPath("$.status").value("400"),
                            jsonPath("$.detail").isNotEmpty(),
                            jsonPath("$.instance").value("/api/v1/categories")
                    );
        }

        @Test
        @DisplayName("when id doen't exists")
        void updateCategoryWhenIdDoesNotExist() throws Exception {
            mockMvc.perform(put("/api/v1/categories")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "id": "3c91cc57-298a-4e88-9b40-a52d4720bb11",
                                      "name": "coffee-new-name"
                                    }
                                    """))
                    .andDo(print())
                    .andExpectAll(
                            status().isNotFound(),
                            jsonPath("$.type").value("about:blank"),
                            jsonPath("$.title").value("Not Found"),
                            jsonPath("$.status").value("404"),
                            jsonPath("$.detail").value("Failed to update category by id: 3c91cc57-298a-4e88-9b40-a52d4720bb11"),
                            jsonPath("$.instance").value("/api/v1/categories")
                    );
        }

    }

    @Nested
    @DisplayName("DELETE /api/v1/categories/:id tests")
    class DeleteCategory {

        @Test
        @DisplayName("when category exists")
        void deleteCategoryWhenCategoryExists() throws Exception {
            mockMvc.perform(delete("/api/v1/categories/{id}", "3c91cc57-298a-4e88-9b40-a52d4720bb93"))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("when category doesn't exist")
        void deleteCategoryWhenCategoryDoesNotExist() throws Exception {
            mockMvc.perform(delete("/api/v1/categories/{id}", "3c91cc57-298a-4e88-9b40-a52d4720bb11"))
                    .andExpectAll(
                            status().isNotFound(),
                            jsonPath("$.type").value("about:blank"),
                            jsonPath("$.title").value("Not Found"),
                            jsonPath("$.status").value("404"),
                            jsonPath("$.detail").value("Failed to delete category by id: 3c91cc57-298a-4e88-9b40-a52d4720bb11"),
                            jsonPath("$.instance").value("/api/v1/categories/3c91cc57-298a-4e88-9b40-a52d4720bb11")
                    );
        }

    }

}