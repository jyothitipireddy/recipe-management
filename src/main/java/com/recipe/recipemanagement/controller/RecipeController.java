package com.recipe.recipemanagement.controller;

import com.recipe.recipemanagement.model.Recipe;
import com.recipe.recipemanagement.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jyothi Tipireddy
 * This REST API is to accept the user requests like create, update, delete and get recipes,
 * validate the user request and forward the call to the service for further processing
 */
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * This method returns all available recipes from the system
     *
     * @return List of available recipes
     */
    @GetMapping
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    /**
     * This method return the details of the given recipe id.
     * If recipe is not then EntityNotFoundException is thrown with http status code 404
     *
     * @param recipeId - Recipe Id
     * @return Recipe details
     */
    @GetMapping("{recipeId}")
    public Recipe getRecipe(@PathVariable Long recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    /**
     * This method update the existing recipe
     *
     * @param recipeId    - Recipe ID
     * @param recipeModel - recipe details to be updated
     */
    @PutMapping("{recipeId}")
    public void updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipeModel) {
        recipeModel.setId(recipeId);
        recipeService.updateRecipe(recipeModel);
    }

    /**
     * This method add a new recipe.
     *
     * @param recipeModel - Recipe details to be stored
     * @return Recipe ID
     */
    @PostMapping
    public Long addRecipe(@RequestBody Recipe recipeModel) {
        return recipeService.addRecipe(recipeModel);
    }

    /**
     * This method deletes the recipe details of the given recipeId
     *
     * @param recipeId - Recipe Id
     */
    @DeleteMapping("{recipeId}")
    public void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}
