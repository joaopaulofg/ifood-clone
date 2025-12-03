package com.joaopaulofg.catalog.application.service;

import com.joaopaulofg.catalog.application.port.output.CategoryRepository;
import com.joaopaulofg.catalog.domain.model.Category;
import com.joaopaulofg.catalog.domain.vo.CategoryId;
import com.joaopaulofg.catalog.infrastructure.input.rest.response.CategoryResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryManagementServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryManagementService categoryManagementService;

    @Test
    void testCreate_Success() {
        String name = "Lanches";
        String description = "Sanduíches e afins";

        Category saved = new Category(CategoryId.generate(), name, description, LocalDateTime.now());
        when(categoryRepository.save(any(Category.class))).thenReturn(saved);
        when(categoryMapper.toResponse(saved)).thenReturn(
                CategoryResponse.builder()
                        .id(saved.getId().toString())
                        .name(saved.getName())
                        .description(saved.getDescription())
                        .creationDate(saved.getCreationDate())
                        .build()
        );

        CategoryResponse response = categoryManagementService.create(name, description);

        assertNotNull(response);
        assertEquals(name, response.getName());
        assertEquals(description, response.getDescription());
        verify(categoryRepository).save(any(Category.class));
        verify(categoryMapper).toResponse(saved);
    }

    @Test
    void testFindCategory_Success() {
        CategoryId id = CategoryId.generate();
        Category category = new Category(id, "Doces", "Sobremesas", LocalDateTime.now());
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryMapper.toResponse(category)).thenReturn(
                CategoryResponse.builder()
                        .id(id.toString())
                        .name(category.getName())
                        .description(category.getDescription())
                        .creationDate(category.getCreationDate())
                        .build()
        );

        CategoryResponse response = categoryManagementService.findCategory(id);

        assertNotNull(response);
        assertEquals("Doces", response.getName());
        verify(categoryRepository).findById(id);
        verify(categoryMapper).toResponse(category);
    }

    @Test
    void testFindCategory_NotFound() {
        CategoryId id = CategoryId.generate();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> categoryManagementService.findCategory(id));
        assertEquals("Category not found with id: " + id.getValue(), ex.getMessage());
        verify(categoryRepository).findById(id);
    }

    @Test
    void testFindAllCategories_Success() {
        Category c1 = new Category(CategoryId.generate(), "C1", "D1", LocalDateTime.now());
        Category c2 = new Category(CategoryId.generate(), "C2", "D2", LocalDateTime.now());

        when(categoryRepository.findAll()).thenReturn(List.of(c1, c2));
        when(categoryMapper.toResponse(c1)).thenReturn(
                CategoryResponse.builder().id(c1.getId().toString()).name(c1.getName()).description(c1.getDescription()).creationDate(c1.getCreationDate()).build());
        when(categoryMapper.toResponse(c2)).thenReturn(
                CategoryResponse.builder().id(c2.getId().toString()).name(c2.getName()).description(c2.getDescription()).creationDate(c2.getCreationDate()).build());

        List<CategoryResponse> responses = categoryManagementService.findAllCategories();

        assertEquals(2, responses.size());
        assertEquals("C1", responses.get(0).getName());
        assertEquals("C2", responses.get(1).getName());
        verify(categoryRepository).findAll();
        verify(categoryMapper).toResponse(c1);
        verify(categoryMapper).toResponse(c2);
    }

    @Test
    void testUpdateCategory_Success() {
        CategoryId id = CategoryId.generate();
        Category category = new Category(id, "Antiga", "Desc antiga", LocalDateTime.now());
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenAnswer(inv -> inv.getArgument(0));
        when(categoryMapper.toResponse(any(Category.class))).thenAnswer(inv -> {
            Category c = inv.getArgument(0);
            return CategoryResponse.builder()
                    .id(c.getId().toString())
                    .name(c.getName())
                    .description(c.getDescription())
                    .creationDate(c.getCreationDate())
                    .build();
        });

        CategoryResponse response = categoryManagementService.updateCategory(id, "Nova", "Desc nova");

        assertEquals("Nova", response.getName());
        assertEquals("Desc nova", response.getDescription());
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testDeleteCategory_NotExists() {
        CategoryId id = CategoryId.generate();
        when(categoryRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> categoryManagementService.deleteCategory(id));
        assertEquals("Category not found with id: " + id.getValue(), ex.getMessage());
        verify(categoryRepository).existsById(id);
        verify(categoryRepository, never()).deleteById(any());
    }

    @Test
    void testDeleteCategory_Success() {
        CategoryId id = CategoryId.generate();
        when(categoryRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> categoryManagementService.deleteCategory(id));
        verify(categoryRepository).deleteById(id);
    }
}