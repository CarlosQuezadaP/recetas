package com.carlosquezada.recipe_dashboard

import com.carlosquezada.recipe_dashboard.domain.exceptions.RecipeException


sealed class Result<out T> {
    data class Success<out R>(val value: R) : Result<R>()
    data class Failure(
        val errorEntity: RecipeException?
    ) : Result<Nothing>()
}