package com.recipe.recipemanagement.controller;

import com.recipe.recipemanagement.enumes.ItemType;
import com.recipe.recipemanagement.model.Ingredient;
import com.recipe.recipemanagement.model.Recipe;
import com.recipe.recipemanagement.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;


    @Test
    @DisplayName("Retrieve all recipes")
    void getRecipes() {
        List<Recipe> list = new ArrayList<>();
        list.add(getPizzaRecipe());
        Mockito.when(recipeService.getRecipes()).thenReturn(list);
        List<Recipe> result = recipeController.getRecipes();
        Assertions.assertEquals(result.size(), 1);

    }

    @Test
    @DisplayName("Retrieve recipe with the recipeId")
    void getRecipe() {
        Mockito.when(recipeService.getRecipe(1L)).thenReturn(getPizzaRecipe());
        Recipe recipe = recipeController.getRecipe(1L);
        Assertions.assertEquals(recipe.getName(), "Pizza");
        Assertions.assertEquals(recipe.getServing(), 4);
    }

    @Test
    @DisplayName("Update recipe")
    void updateRecipe() {
        recipeController.updateRecipe(1L, getPizzaRecipe());
        verify(recipeService, times(1)).updateRecipe(Mockito.any(Recipe.class));
    }

    @Test
    @DisplayName("Add recipe")
    void addRecipe() {
        Mockito.when(recipeService.addRecipe(Mockito.any(Recipe.class))).thenReturn(1L);
        Long recipeID = recipeController.addRecipe(getPizzaRecipe());
        Assertions.assertEquals(recipeID, 1L);
    }

    @Test
    @DisplayName("Delete recipe")
    void deleteRecipe() {
        recipeController.deleteRecipe(1L);
        verify(recipeService, times(1)).deleteRecipe(1L);
    }

    private Recipe getPizzaRecipe() {
        Recipe pizza = new Recipe();

        pizza.setId(1L);
        pizza.setItemType(ItemType.NON_VEG);
        pizza.setDescription("Chicken Pizza");
        pizza.setCookingInstructions("Instructions");
        pizza.setServing(4);
        pizza.setName("Pizza");

        Ingredient salt = new Ingredient();
        salt.setId(1L);
        salt.setName("Salt");
        salt.setDescription("Salt");

        Ingredient oil = new Ingredient();
        oil.setId(2L);
        oil.setName("Oil");
        oil.setDescription("Oil");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(salt);
        ingredients.add(oil);

        pizza.setIngredients(ingredients);
        return pizza;
    }
}