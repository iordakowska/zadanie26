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
    private CategoryRepository categoryRepository;

    public RecipeController(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
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

    @GetMapping("/potrawa/{id}/usun")
    public String delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "deleteInfo";
    }

    @GetMapping("/potrawa/{id}/polubienia")
    public String likeIt(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipe.setLikes(recipe.getLikes() + 1);
            recipeRepository.save(recipe);
            model.addAttribute("recipe", recipe);
            return "redirect:/potrawa/{id}";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("recipe", new Recipe());
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("category", categoryList);
        return "form";
    }

    @PostMapping("/add")
    public String addRecipe(Recipe recipe) {
        if (!recipe.getName().isEmpty()) {
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
        if (!recipe.getName().isEmpty()) {
            recipeRepository.save(recipe);
            return "redirect:/";
        } else {
            return "redirect:/potrawa/" + recipe.getId();
        }
    }

    @GetMapping("/top")
    public String topRecipes(Model model) {

        List<Recipe> recipeList = recipeRepository.findRecipesByOrderByLikesDesc();
        List<Recipe> topList = recipeList.subList(0, 5);
        model.addAttribute("recipeList", topList);
        return "top";
    }

    private List<Recipe> showTopRecipe(List<Recipe> list, int limit) {
        List<Recipe> topList = list.subList(0, limit - 1);
        return topList;
    }
}
