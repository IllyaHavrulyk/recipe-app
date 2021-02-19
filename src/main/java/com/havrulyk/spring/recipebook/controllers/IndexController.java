package com.havrulyk.spring.recipebook.controllers;

import com.havrulyk.spring.recipebook.domain.Category;
import com.havrulyk.spring.recipebook.domain.UnitOfMeasure;
import com.havrulyk.spring.recipebook.repositories.CategoryRepository;
import com.havrulyk.spring.recipebook.repositories.UnitOfMeasureRepository;
import com.havrulyk.spring.recipebook.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {
    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/", "home", "index", ""})
    public String getIndexPage(Model model) {
        log.debug("Getting index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
