package com.recipe.recipemanagement.service;

import com.recipe.recipemanagement.entity.RecipeEntity;
import com.recipe.recipemanagement.mapper.RecipeMapper;
import com.recipe.recipemanagement.model.Recipe;
import com.recipe.recipemanagement.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jyothi Tipireddy
 * This class maps the user input to the entities and interact with the Respository to insert, update, delete and get recipes
 */
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;


    /**
     * public constructor
     *
     * @param recipeRepository - RecipeRepository
     * @param recipeMapper     - RecipeMapper
     */
    @Autowired
    public RecipeService(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    /**
     * This method retrieve recipes based on the given name, if no name is present then all available recipes will retrieve
     *
     * @return - List of recipes
     */
    public List<Recipe> getRecipes() {
        var recipeEntities = recipeRepository.findAll();

        return recipeEntities.stream().map(recipeEntity -> {
            Recipe recipe = new Recipe();
            recipeMapper.entityToModel(recipeEntity, recipe);
            return recipe;
        }).collect(Collectors.toList());
    }

    /**
     * This method retrieve the recipe details of the given recipe id
     *
     * @param recipeId - Recipe Id
     * @return - Recipe Details
     */
    public Recipe getRecipe(Long recipeId) {
        var recipeEntity = recipeRepository.findById(recipeId).orElseThrow(() -> new EntityNotFoundException("The Recipe id {" + recipeId + "} is not present"));

        var recipe = new Recipe();
        recipeMapper.entityToModel(recipeEntity, recipe);
        return recipe;
    }

    /**
     * This method is to update the recipe
     *
     * @param recipeModel - Recipe details to update
     */
    @Transactional
    public void updateRecipe(Recipe recipeModel) {
        var recipeEntity = recipeRepository.findById(recipeModel.getId()).orElseThrow(() -> new EntityNotFoundException("Recipe is not present"));
        recipeMapper.modelToEntity(recipeModel, recipeEntity);
        recipeRepository.save(recipeEntity);
    }

    /**
     * This method is to create new recipe
     *
     * @param recipeModel Recipe model
     * @return recipe Id
     */
    @Transactional
    public Long addRecipe(Recipe recipeModel) {

        var recipeEntity = new RecipeEntity();
        recipeMapper.modelToEntity(recipeModel, recipeEntity);

        var entity = recipeRepository.save(recipeEntity);
        return entity.getId();
    }

    /**
     * This method is to delete the recipe details of the given recipe id
     *
     * @param recipeId - Recipe ID
     */
    @Transactional
    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
