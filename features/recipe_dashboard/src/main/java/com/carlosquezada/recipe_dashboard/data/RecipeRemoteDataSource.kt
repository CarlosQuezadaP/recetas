package com.carlosquezada.recipe_dashboard.data

import com.carlosquezada.recipe_dashboard.Result
import com.carlosquezada.core.domain.ErrorHandler
import com.carlosquezada.core.domain.NetworkException
import com.carlosquezada.recipe_dashboard.data.api.RecipeApi
import com.carlosquezada.recipe_dashboard.data.mapper.toDomain
import com.carlosquezada.recipe_dashboard.domain.RecipeDomain
import com.carlosquezada.recipe_dashboard.domain.exceptions.RecipeException

class RecipeRemoteDataSource(
    private val recipeApi: RecipeApi,
    private val errorHandler: ErrorHandler,
) {

    suspend fun getAllRecipes():
            Result<List<RecipeDomain>> {
        return try {
            val recipes = recipeApi.getRecipes().recipeDtos.map { it.toDomain() }
            Result.Success(recipes)
        } catch (e: Exception) {
            val exception = if (e is NetworkException) {
                RecipeException.RecipeNotAvailable()
            } else {
                RecipeException.NotFoundError(errorHandler.getError(e))
            }
            Result.Failure(exception)
        }
    }
}