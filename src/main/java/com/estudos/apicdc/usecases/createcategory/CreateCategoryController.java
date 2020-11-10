package com.estudos.apicdc.usecases.createcategory;

import com.estudos.apicdc.domain.Category;
import com.estudos.apicdc.domain.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CreateCategoryController {

    private final CategoryRepository categoryRepository;

    public CreateCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/categories")
    @Transactional
    public void createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        Category newCategory = request.toModel();
        if (categoryRepository.existsByName(request.name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with same name already exists");
        }
        categoryRepository.save(newCategory);
    }
}
