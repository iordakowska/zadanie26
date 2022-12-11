package com.example.zadanie26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    private RecipeRepository recipeRepository;

    public CategoryController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/category")
    public String recipeByCategory(Model model, @RequestParam(required = false) Category category) {

        List<Recipe> recipeList = recipeRepository.findByCategory(category);
        model.addAttribute("recipeList", recipeList);
        model.addAttribute(category);
        return "category";
    }
}
