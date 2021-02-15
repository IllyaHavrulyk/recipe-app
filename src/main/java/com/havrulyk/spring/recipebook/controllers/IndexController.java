package com.havrulyk.spring.recipebook.controllers;

import com.havrulyk.spring.recipebook.domain.Category;
import com.havrulyk.spring.recipebook.domain.UnitOfMeasure;
import com.havrulyk.spring.recipebook.repositories.CategoryRepository;
import com.havrulyk.spring.recipebook.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"/", "home", "index", ""})
    public String getIndexPage(Model model) {
        Optional<Category> category = categoryRepository.findByDescription("American");
        System.out.println(category.get().getDescription() + " ID : " + category.get().getId());
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Tablespoon");
        System.out.println(uom.get().getDescription() + " ID : " + uom.get().getId());
        model.addAttribute("home-page-greeting", "You're on a home page");
        return "index";
    }
}
