package com.carlosquezada.recipe_dashboard.presentation.models

data class RecipeDetailModel(
    val name: String,
    val imageUrl: String,
    val description: String,
    val stepsForPreparation: List<String>
)