package com.recipe.recipemanagement.service;

import com.recipe.recipemanagement.entity.IngredientEntity;
import com.recipe.recipemanagement.model.Ingredient;
import com.recipe.recipemanagement.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jyothi Tipireddy
 * Service class to deal with Ingredients
 */
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    /**
     * public constructor
     *
     * @param ingredientRepository IngredientRepository
     */
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * This method returns all available Ingredients from the system
     *
     * @return List of available Ingredients
     */
    public List<Ingredient> getIngredients() {

        return ingredientRepository.findAll().stream().map(ingredientEntity -> {
            var ingredientDto = new Ingredient();
            ingredientDto.setId(ingredientEntity.getId());
            ingredientDto.setName(ingredientEntity.getName());
            ingredientDto.setDescription(ingredientEntity.getDescription());
            return ingredientDto;
        }).collect(Collectors.toList());
    }

    /**
     * This method return the details of the given Ingredient id.
     * If Ingredient is not available then EntityNotFoundException is thrown with http status code 404
     *
     * @param ingredientId - Ingredient Id
     * @return Ingredient details
     */
    public Ingredient getIngredient(Long ingredientId) {

        var ingredientEntity = ingredientRepository.findById(ingredientId).orElseThrow(() -> new EntityNotFoundException("The Ingredient id {" + ingredientId + "} is not present"));
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientEntity.getId());
        ingredient.setName(ingredientEntity.getName());
        ingredient.setDescription(ingredientEntity.getDescription());
        return ingredient;

    }

    /**
     * This method add a new Ingredient.
     *
     * @param ingredient - Ingredient details to be stored
     * @return Ingredient ID
     */
    @Transactional
    public Long createIngredient(Ingredient ingredient) {

        var ingredientEntity = new IngredientEntity();
        ingredientEntity.setName(ingredient.getName());
        ingredientEntity.setDescription(ingredient.getDescription());
        return ingredientRepository.save(ingredientEntity).getId();
    }

    /**
     * This method update the existing Ingredient
     *
     * @param ingredient - Ingredient details to be updated
     */
    @Transactional
    public void updateIngredient(Ingredient ingredient) {
        var ingredientEntity = ingredientRepository.findById(ingredient.getId()).orElseThrow(() -> new EntityNotFoundException("The Ingredient id {" + ingredient.getId() + "} is not present"));
        ingredientEntity.setName(ingredient.getName());
        ingredientEntity.setDescription(ingredient.getDescription());
        ingredientRepository.save(ingredientEntity);
    }
}
