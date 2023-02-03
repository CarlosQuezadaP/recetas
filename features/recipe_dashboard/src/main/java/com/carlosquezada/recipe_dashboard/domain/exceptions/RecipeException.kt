package com.carlosquezada.recipe_dashboard.domain.exceptions

import androidx.annotation.StringRes
import com.carlosquezada.core.domain.ErrorEntity
import com.carlosquezada.recipe_dashboard.R

sealed class RecipeException : ErrorEntity() {

    class RecipeNotAvailable(@StringRes val errorMessage: Int = R.string.recipe_not_available) :
        RecipeException()

    class UnknownError(@StringRes val errorMessage: Int = R.string.unknown_error) :
        RecipeException()

    class NotFoundError(val error: ErrorEntity) : RecipeException()

    class EmptyRecipes(@StringRes val errorMessage: Int = R.string.empty_recipes) :
        RecipeException()

}
