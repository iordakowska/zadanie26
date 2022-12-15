package com.example.zadanie26;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategory(Category category);

    List<Recipe> findAllByOrderByLikesDesc();

    List<Recipe> findRecipesByOrderByLikesDesc();
}