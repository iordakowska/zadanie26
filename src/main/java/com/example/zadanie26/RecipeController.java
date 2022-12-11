package com.example.zadanie26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/wszystkie")
    public String allRecipes(Model model) {

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
            return "redirect:/404";
        }
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("recipe", new Recipe());
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
            model.addAttribute("recipe", recipe);
            return "form";
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

    @GetMapping("/potrawa/{id}/usun")
    public String delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "deleteInfo";
    }

    /*@GetMapping("/potrawa/{id}/polubienia}")
    public String likeIt(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipeRepository.save(recipe);
            model.addAttribute("recipe", recipe);
            return "redirect:/potrawa/{id}";
        } else {
            return "redirect:/";
        }
    }*/

    @GetMapping("/top")
    public String topRecipes(Model model, String name) {

        List<Recipe> recipeList = recipeRepository.findAll(); //testowo - nie działa inna metoda
        model.addAttribute("recipeList", recipeList);
        return "all";
    }

}
