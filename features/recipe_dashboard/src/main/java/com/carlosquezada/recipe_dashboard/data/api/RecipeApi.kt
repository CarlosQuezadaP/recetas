package com.carlosquezada.recipe_dashboard.data.api

import com.carlosquezada.core.URL_BASE
import com.carlosquezada.recipe_dashboard.data.response.RecipeResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


class RecipeApi(private val client: HttpClient) {

    suspend fun getRecipes() : RecipeResponse = client.get(URL_BASE).body()
}
