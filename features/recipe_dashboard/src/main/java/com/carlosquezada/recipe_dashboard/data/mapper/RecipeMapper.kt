package com.carlosquezada.recipe_dashboard.data.mapper

import com.carlosquezada.recipe_dashboard.data.response.RecipeDto
import com.carlosquezada.recipe_dashboard.domain.IngredientDomain
import com.carlosquezada.recipe_dashboard.domain.RecipeDomain

fun RecipeDto.toDomain(): RecipeDomain = RecipeDomain(
    imageUrl = this.imageURL,
    ingredients = this.ingredientVOS.map {
        IngredientDomain(
            name = it.name,
            quantity = it.quantity,
            type = it.type
        )
    },
    name = this.name,
    originalURL = this.originalURL,
    steps = this.steps,
    timers = this.timers,
)