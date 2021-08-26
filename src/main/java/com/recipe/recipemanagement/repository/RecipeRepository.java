package com.recipe.recipemanagement.repository;

import com.recipe.recipemanagement.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jyothi Tipireddy
 * Repository class to interact with the Recipe table
 */
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

}
