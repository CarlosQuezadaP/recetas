package com.carlosquezada.recipe_dashboard.data

import com.carlosquezada.recipe_dashboard.domain.IngredientDomain
import com.carlosquezada.recipe_dashboard.domain.RecipeDomain

class RecipeLocalDataSource {

    private var recipes: MutableList<RecipeDomain> = mutableListOf()

    fun recipesIsEmpty(): Boolean {
        return this.recipes.isEmpty()
    }

    fun setRecipesData(recipes: List<RecipeDomain>) {
        this.recipes = mutableListOf<RecipeDomain>().apply {
            addAll(recipes)
        }
    }

    fun getLocalRecipes(): List<RecipeDomain> {
        return this.recipes.map { it }
    }

    fun getRecipesBySearch(
        query: String,
        isNameFilterSelected: Boolean
    ): List<RecipeDomain> {
        if (query.isEmpty()) {
            return getLocalRecipes()
        }
        return if (isNameFilterSelected) {
            getLocalRecipes().filter {
                it.name.lowercase().contains(query.lowercase())
            }
        } else {
            getLocalRecipes().filter {
                ingredientIsPresent(query, it.ingredients)
            }
        }
    }

    fun getRecipeByName(name: String): RecipeDomain? =
        getLocalRecipes().find {
            it.name == name
        }

    private fun ingredientIsPresent(query: String, ingredients: List<IngredientDomain>): Boolean {
        return !ingredients.none { it.name.lowercase() == query.lowercase() }
    }
}
