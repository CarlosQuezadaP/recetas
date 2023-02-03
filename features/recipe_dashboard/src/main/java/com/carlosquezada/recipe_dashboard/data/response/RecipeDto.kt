package com.carlosquezada.recipe_dashboard.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("imageURL")
    val imageURL: String,
    @SerialName("ingredients")
    val ingredientVOS: List<IngredientVO>,
    @SerialName("name")
    val name: String,
    @SerialName("originalURL")
    val originalURL: String,
    @SerialName("steps")
    val steps: List<String>,
    @SerialName("timers")
    val timers: List<Int>
)