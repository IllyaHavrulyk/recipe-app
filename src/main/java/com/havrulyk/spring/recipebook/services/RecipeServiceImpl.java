package com.havrulyk.spring.recipebook.services;

import com.havrulyk.spring.recipebook.commands.RecipeCommand;
import com.havrulyk.spring.recipebook.converters.RecipeCommandToRecipe;
import com.havrulyk.spring.recipebook.converters.RecipeToRecipeCommand;
import com.havrulyk.spring.recipebook.domain.Recipe;
import com.havrulyk.spring.recipebook.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
    this.recipeRepository = recipeRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Set<Recipe> getRecipes() {
    log.debug("I'm in the service.");
    Set<Recipe> recipes = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
    return recipes;
  }

  @Override
  public Recipe findById(Long id){
    Optional<Recipe> recipeOptional = recipeRepository.findById(id);
    if(!recipeOptional.isPresent()){
      throw new RuntimeException("Recipe with given id doesn't exist");
    }
    return recipeOptional.get();
  }

  @Override
  @Transactional
  public RecipeCommand saveRecipeCommand(RecipeCommand command) {
    Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

    Recipe savedRecipe = recipeRepository.save(detachedRecipe);
    log.debug("Saved RecipeId:" + savedRecipe.getId());
    return recipeToRecipeCommand.convert(savedRecipe);
  }
}
