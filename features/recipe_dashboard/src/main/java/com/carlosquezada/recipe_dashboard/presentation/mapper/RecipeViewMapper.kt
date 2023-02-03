package com.carlosquezada.recipe_dashboard.presentation.mapper

import com.carlosquezada.recipe_dashboard.domain.RecipeDomain
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeDetailModel
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeHomeModel

fun RecipeDomain.toHomeModel(): RecipeHomeModel = RecipeHomeModel(this.name, this.imageUrl)

fun RecipeDomain.toDetailModel(): RecipeDetailModel =
    RecipeDetailModel(this.name, this.imageUrl, this.steps[0], this.steps)