package com.havrulyk.spring.recipebook.repositories;

import com.havrulyk.spring.recipebook.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
