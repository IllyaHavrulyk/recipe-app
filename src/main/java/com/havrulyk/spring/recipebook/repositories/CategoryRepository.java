package com.havrulyk.spring.recipebook.repositories;

import com.havrulyk.spring.recipebook.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
