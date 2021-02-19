package com.havrulyk.spring.recipebook.bootstrap;

import com.havrulyk.spring.recipebook.domain.Category;
import com.havrulyk.spring.recipebook.domain.Difficulty;
import com.havrulyk.spring.recipebook.domain.Ingredient;
import com.havrulyk.spring.recipebook.domain.Notes;
import com.havrulyk.spring.recipebook.domain.Recipe;
import com.havrulyk.spring.recipebook.domain.UnitOfMeasure;
import com.havrulyk.spring.recipebook.repositories.CategoryRepository;
import com.havrulyk.spring.recipebook.repositories.RecipeRepository;
import com.havrulyk.spring.recipebook.repositories.UnitOfMeasureRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final CategoryRepository categoryRepository;
  private final RecipeRepository recipeRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
    this.categoryRepository = categoryRepository;
    this.recipeRepository = recipeRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    recipeRepository.saveAll(getRecipes());
    log.debug("Loading Bootstrap Data");
  }

  private List<Recipe> getRecipes() {
    List<Recipe> recipes = new ArrayList<>(2);
    String[] unitDescriptions = {"Teaspoon", "Tablespoon", "Cup", "Pinch", "Ounce", "Each"};
    Arrays.stream(unitDescriptions).peek(unitDescription -> {
      if (!unitOfMeasureRepository.findByDescription(unitDescription).isPresent()) {
        throw new RuntimeException("Cannot find that type of UOM.");
      }
    });
    //get optionals
    UnitOfMeasure eachUom = unitOfMeasureRepository.findByDescription("Each").get();
    UnitOfMeasure teaSpoonUom = unitOfMeasureRepository.findByDescription("Teaspoon").get();
    UnitOfMeasure cupUom = unitOfMeasureRepository.findByDescription("Cup").get();
    UnitOfMeasure pinchUom = unitOfMeasureRepository.findByDescription("Pinch").get();
    UnitOfMeasure ounceUom = unitOfMeasureRepository.findByDescription("Ounce").get();
    UnitOfMeasure tableSpoonUom = unitOfMeasureRepository.findByDescription("Tablespoon").get();

    Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
    if (!americanCategoryOptional.isPresent()) {
      throw new RuntimeException("Expected Category not found.");
    }
    Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
    if (!mexicanCategoryOptional.isPresent()) {
      throw new RuntimeException("Expected Category not found.");
    }
    Category americanCategory = americanCategoryOptional.get();
    Category mexicanCategory = mexicanCategoryOptional.get();

    //Yummy Grechka
    Recipe grechka = new Recipe();
    grechka.setServings(1);
    grechka.setDescription("Smachna Grechka!");
    grechka.setPrepTime(2);
    grechka.setCookTime(10);
    grechka.setDifficulty(Difficulty.EASY);
    grechka.setDirections("Beresh grechku v baneka zaluvaesh vodu i grechku 10hv");
    Notes grechkaNotes = new Notes();
    grechkaNotes.setRecipeNotes("Duzhe shvudko gotuyetsa i smachno yistsya.");
    grechkaNotes.setRecipe(grechka);
    grechka.setNotes(grechkaNotes);
    grechka.addIngredient(new Ingredient("Grechani zerna", new BigDecimal(2), eachUom));
    grechka.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
    grechka.getCategories().add(americanCategory);
    grechka.getCategories().add(mexicanCategory);

    //add to return list;
    recipes.add(grechka);

    //yummy tacos
    Recipe tacos = new Recipe();
    tacos.setServings(2);
    tacos.setDescription("Yummy tacos!");
    tacos.setPrepTime(1);
    tacos.setCookTime(5);
    tacos.setDifficulty(Difficulty.MODERATE);
    tacos.setDirections("\n1. Get some taco bread.\n2. Cook the taco!");
    Notes tacoNotes = new Notes();
    tacoNotes.setRecipeNotes("Taco is extremely tasteful mexican food.");
    tacos.setNotes(tacoNotes);
    tacoNotes.setRecipe(tacos);

    tacos.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
    tacos.getIngredients().add(new Ingredient("Bread Poder", new BigDecimal("0.2"), teaSpoonUom));

    //set tacos category to mexican
    tacos.getCategories().add(americanCategory);
    tacos.getCategories().add(mexicanCategory);

    //add to return list
    recipes.add(tacos);
    return recipes;
  }

}
