package com.carlosquezada.recipe_dashboard.domain

import com.carlosquezada.recipe_dashboard.Result
import com.carlosquezada.recipe_dashboard.data.RecipeRepository

class RecipesUseCase(private val recipeRepository: RecipeRepository) {

    suspend fun getAllRecipes(): Result<List<RecipeDomain>> = recipeRepository.getAllRecipes()

    fun getRecipesBySearch(
        query: String,
        isNameFilterSelected: Boolean
    ): Result<List<RecipeDomain>> {
        return recipeRepository.getRecipesBySearch(query.lowercase(), isNameFilterSelected)
    }

    fun getRecipeByName(name: String): Result<RecipeDomain> {
        return recipeRepository.getRecipeByName(name)
    }

}