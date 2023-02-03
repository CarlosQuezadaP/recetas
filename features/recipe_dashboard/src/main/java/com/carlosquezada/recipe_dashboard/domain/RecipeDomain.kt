package com.carlosquezada.recipe_dashboard.domain

data class RecipeDomain(
    var imageUrl: String,
    var ingredients : List<IngredientDomain>,
    var name: String,
    var originalURL: String,
    var steps: List<String>,
    var timers: List<Int>,

)