package com.recipe.recipemanagement.mapper;

import com.recipe.recipemanagement.entity.IngredientEntity;
import com.recipe.recipemanagement.entity.RecipeEntity;
import com.recipe.recipemanagement.entity.RecipeIngredientEntity;
import com.recipe.recipemanagement.entity.RecipeIngredientId;
import com.recipe.recipemanagement.enumes.ItemType;
import com.recipe.recipemanagement.model.Ingredient;
import com.recipe.recipemanagement.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jyothi Tipireddy
 * Mapper class to map entity to model and mode to entity
 */
@Component
public class RecipeMapper {

    /**
     * This method converts Recipe model to Recipe entity
     *
     * @param recipe       - RecipeDto
     * @param recipeEntity - RecipeEntity
     */
    public void modelToEntity(Recipe recipe, RecipeEntity recipeEntity) {

        recipeEntity.setDescription(recipe.getDescription());
        recipeEntity.setCookingInstructions(recipe.getCookingInstructions());
        recipeEntity.setItemType(recipe.getItemType().name());
        recipeEntity.setName(recipe.getName());
        recipeEntity.setServing(recipe.getServing());
        var newIngredients = recipe.getIngredients();
        //Convert Ingredient model into IngredientEntity and RecipeIngredientEntity
        var list = newIngredients.stream().map(ingredientDto -> {

            var recipeIngredientEntity = new RecipeIngredientEntity();
            var recipeIngredientId = new RecipeIngredientId();
            recipeIngredientId.setRecipe(recipeEntity);
            IngredientEntity ingredientEntity = new IngredientEntity();
            ingredientEntity.setId(ingredientDto.getId());
            ingredientEntity.setName(ingredientDto.getName());
            ingredientEntity.setDescription(ingredientDto.getDescription());
            recipeIngredientId.setIngredient(ingredientEntity);
            recipeIngredientEntity.setQuantity(ingredientDto.getQuantity());
            recipeIngredientEntity.setRecipeIngredientId(recipeIngredientId);

            return recipeIngredientEntity;
        }).collect(Collectors.toList());

        recipeEntity.setRecipeIngredients(list);
    }

    /**
     * This method converts Entity to Model
     *
     * @param recipeEntity - RecipeEntity
     * @param recipe       - RecipeDto
     */
    public void entityToModel(RecipeEntity recipeEntity, Recipe recipe) {

        recipe.setId(recipeEntity.getId());
        recipe.setCookingInstructions(recipeEntity.getCookingInstructions());
        recipe.setCreatedOn(recipeEntity.getCreatedOn());
        recipe.setDescription(recipeEntity.getDescription());
        recipe.setItemType(ItemType.valueOf(recipeEntity.getItemType()));
        recipe.setName(recipeEntity.getName());
        recipe.setServing(recipeEntity.getServing());
        var recipeIngredientEntity = recipeEntity.getRecipeIngredients();
        List<Ingredient> recipeIngredients = new ArrayList<>();
        recipeIngredientEntity.forEach(recipeIngredientEntity1 -> {
            var ingredient = new Ingredient();
            ingredient.setId(recipeIngredientEntity1.getRecipeIngredientId().getIngredient().getId());
            ingredient.setDescription(recipeIngredientEntity1.getRecipeIngredientId().getIngredient().getDescription());
            ingredient.setName(recipeIngredientEntity1.getRecipeIngredientId().getIngredient().getName());
            ingredient.setQuantity(recipeIngredientEntity1.getQuantity());
            recipeIngredients.add(ingredient);
        });
        recipe.setIngredients(recipeIngredients);
    }
}
