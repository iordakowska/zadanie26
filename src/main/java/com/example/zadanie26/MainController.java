package com.example.zadanie26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private RecipeRepository recipeRepository;

    public MainController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /*@GetMapping("/")
    public String home() {
        return "home";
    }*/

    @GetMapping("/all")
    public String allRecipes(Model model, @RequestParam(required = false) Category category) {

        List<Recipe> recipeList = recipeRepository.findAll();
        model.addAttribute("recipeList", recipeList);
        return "all";
    }

    @GetMapping("/potrawa/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/")
    public String recipeByCategory(Model model, @RequestParam(required = false) Category category) {

        List<Recipe> recipeList = recipeRepository.findByCategory(category);
        model.addAttribute("recipeList", recipeList);
        return "home";
    }




}
