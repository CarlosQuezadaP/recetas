package com.carlosquezada.recipe_dashboard.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosquezada.recipe_dashboard.Result
import com.carlosquezada.recipe_dashboard.domain.RecipesUseCase
import com.carlosquezada.recipe_dashboard.presentation.mapper.toDetailModel
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeDetailModel
import kotlinx.coroutines.launch

class DetailRecipeViewModel(private val recipesUseCase: RecipesUseCase) : ViewModel() {

    private val _recipe = MutableLiveData<RecipeDetailModel>()

    val recipe: LiveData<RecipeDetailModel>
        get() = _recipe

    private fun setRecipe(recipe: RecipeDetailModel) {
        _recipe.value = recipe
    }

    fun getRecipeByName(recipeName: String) {
        viewModelScope.launch {
            when (val recipes = recipesUseCase.getRecipeByName(recipeName)) {
                is Result.Success -> {
                    setRecipe(recipes.value.toDetailModel())
                }
                is Result.Failure -> {
                    //Nothing
                }
            }
        }
    }
}