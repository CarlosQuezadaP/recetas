package com.carlosquezada.recipe_dashboard.presentation.ui.home.listener

import com.carlosquezada.recipe_dashboard.presentation.models.RecipeHomeModel

interface RecipeClickListener {
    fun onClick(recipeHomeModel: RecipeHomeModel)
}