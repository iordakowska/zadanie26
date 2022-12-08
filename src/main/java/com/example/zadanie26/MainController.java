package com.example.zadanie26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private RecipeRepository recipeRepository;

    public MainController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

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

    @GetMapping("/category")
    public String recipeByCategory(Model model, @RequestParam(required = false) Category category) {

        List<Recipe> recipeList = recipeRepository.findByCategory(category);
        model.addAttribute("recipeList", recipeList);
        model.addAttribute(category);
        return "category";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("recipeToAdd", new Recipe());
        return "form";
    }

    @PostMapping("/add")
    public String addRecipe(Recipe recipe) {
        if(!recipe.getName().isEmpty()) {
            recipeRepository.save(recipe);
            return "redirect:/";
        } else {
            return "redirect: /error";
        }
    }

    @GetMapping("/potrawa/{id}/edit")
    public String showRecipeToEdit(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipeToEdit", recipe);
            return "recipeEdit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/potrawa/edit")
    public String editRecipe(Recipe recipe) {
        if(!recipe.getName().isEmpty()) {
            recipeRepository.save(recipe);
            return "redirect:/";
        } else {
            return "redirect:/potrawa/" + recipe.getId();
        }
    }












}
