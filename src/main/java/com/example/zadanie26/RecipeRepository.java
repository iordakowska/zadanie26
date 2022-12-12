package com.example.zadanie26;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategory(Category category);

    //@Query("SELECT Recipe FROM Recipe order by likes DESC")
    //List<Recipe> topList() = ;

    List<Recipe> findAllByOrderByLikesDesc();
}