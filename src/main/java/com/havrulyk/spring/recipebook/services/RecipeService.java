package com.havrulyk.spring.recipebook.services;

import com.havrulyk.spring.recipebook.commands.RecipeCommand;
import com.havrulyk.spring.recipebook.domain.Recipe;
import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
