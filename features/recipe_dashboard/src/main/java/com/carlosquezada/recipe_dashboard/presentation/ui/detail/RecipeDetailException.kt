package com.carlosquezada.recipe_dashboard.presentation.ui.detail

import androidx.annotation.StringRes
import com.carlosquezada.core.domain.ErrorEntity
import com.carlosquezada.recipe_dashboard.R

sealed class RecipeDetailException : ErrorEntity() {

    class DescriptionNotAvailable(@StringRes val errorMessage: Int = R.string.recipeDetailNotFound) :
        RecipeDetailException()

    class UnknownError(val error: ErrorEntity) : RecipeDetailException()
}