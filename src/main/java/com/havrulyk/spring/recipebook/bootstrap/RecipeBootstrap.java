package com.havrulyk.spring.recipebook.bootstrap;

import com.havrulyk.spring.recipebook.domain.*;
import com.havrulyk.spring.recipebook.repositories.CategoryRepository;
import com.havrulyk.spring.recipebook.repositories.RecipeRepository;
import com.havrulyk.spring.recipebook.repositories.UnitOfMeasureRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RecipeBootstrap {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);
        String[] unitDescriptions = {"Teaspoon", "Tablespoon", "Cup", "Pinch", "Ounce", "Each"};
        Arrays.stream(unitDescriptions).peek(unitDescription -> {
           if(!unitOfMeasureRepository.findByDescription(unitDescription).isPresent()){
               throw  new RuntimeException("Cannot find that type of UOM.");
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
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found.");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found.");
        }
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Grechka
        Recipe grechka = new Recipe();
        grechka.setDescription("Smachna Grechka!");
        grechka.setPrepTime(2);
        grechka.setCookTime(10);
        grechka.setDifficulty(Difficulty.EASY);
        grechka.setDirections("Beresh grechku v baneka zaluvaesh vodu i grechku 10hv");
        Notes notes = new Notes();
        //todo: finish bootstraping
        return null;
    }

}
