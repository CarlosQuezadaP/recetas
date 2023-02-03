package com.carlosquezada.recipe_dashboard.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientVO(
    @SerialName("name")
    val name: String,
    @SerialName("quantity")
    val quantity: String,
    @SerialName("type")
    val type: String
)