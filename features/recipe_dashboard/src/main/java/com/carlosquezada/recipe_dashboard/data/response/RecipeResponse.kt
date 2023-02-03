package com.carlosquezada.recipe_dashboard.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val recipeDtos: List<RecipeDto>
)