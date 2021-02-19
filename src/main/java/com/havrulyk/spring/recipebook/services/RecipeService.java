package com.havrulyk.spring.recipebook.services;

import com.havrulyk.spring.recipebook.domain.Recipe;
import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();
}
