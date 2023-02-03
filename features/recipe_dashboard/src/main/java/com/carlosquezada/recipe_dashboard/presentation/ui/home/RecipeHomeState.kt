package com.carlosquezada.recipe_dashboard.presentation.ui.home

import com.carlosquezada.recipe_dashboard.domain.exceptions.RecipeException
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeHomeModel

sealed class RecipeHomeState {

    object Loading : RecipeHomeState()

    class Success(val recipes: List<RecipeHomeModel>) : RecipeHomeState()

    class Failure(val error: RecipeException) : RecipeHomeState()
}
