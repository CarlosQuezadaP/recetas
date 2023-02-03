package com.carlosquezada.recipe_dashboard.data

import com.carlosquezada.recipe_dashboard.Result
import com.carlosquezada.recipe_dashboard.domain.RecipeDomain
import com.carlosquezada.recipe_dashboard.domain.exceptions.RecipeException

class RecipeRepository(
    private val recipeLocalData: RecipeLocalDataSource,
    private val recipeRemoteData: RecipeRemoteDataSource
) {

    suspend fun getAllRecipes(): Result<List<RecipeDomain>> {

        return if (recipeLocalData.recipesIsEmpty()) {
            fetchRecipes()
        } else {
            val recipes = recipeLocalData.getLocalRecipes()
            Result.Success(recipes)
        }
    }

    fun getRecipeByName(name: String): Result<RecipeDomain> {
        val recipe = recipeLocalData.getRecipeByName(name)
        return recipe?.let {
            Result.Success(it)
        } ?: Result.Failure(RecipeException.EmptyRecipes())
    }

    fun getRecipesBySearch(
        query: String,
        isNameFilterSelected: Boolean
    ): Result<List<RecipeDomain>> {
        return if (recipeLocalData.recipesIsEmpty()) {
            Result.Failure(RecipeException.EmptyRecipes())
        } else {
            val data = recipeLocalData.getRecipesBySearch(query, isNameFilterSelected)
            return if (data.isEmpty()) {
                Result.Failure(RecipeException.EmptyRecipes())
            } else {
                Result.Success(data)
            }
        }
    }


    private suspend fun fetchRecipes(): Result<List<RecipeDomain>> {
        val result = recipeRemoteData.getAllRecipes()
        when (result) {
            is Result.Success -> {
                recipeLocalData.setRecipesData(result.value)
            }
            is Result.Failure -> {
                result.errorEntity
            }
        }
        return result
    }


}