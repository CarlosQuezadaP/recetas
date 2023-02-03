package com.carlosquezada.recipe_dashboard.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carlosquezada.recipe_dashboard.Result
import com.carlosquezada.recipe_dashboard.domain.RecipesUseCase
import com.carlosquezada.recipe_dashboard.presentation.BaseViewModelWithState
import com.carlosquezada.recipe_dashboard.presentation.mapper.toHomeModel

class HomeRecipeViewModel(private val recipesUseCase: RecipesUseCase) :
    BaseViewModelWithState<RecipeHomeState>(RecipeHomeState.Loading) {

    private val _queryRecipe = MutableLiveData<String>()

    val recipeQuery: LiveData<String>
        get() = _queryRecipe

    fun setQueryRecipe(query: String?) {
        query?.let {
            _queryRecipe.value = it
        }
    }

    private val _isNameOfRecipeFilterSelected = MutableLiveData<Boolean>()

    fun setIsNameOfRecipeFilterSelected(state: Boolean) {
        _isNameOfRecipeFilterSelected.value = state
    }

    fun getAllRecipeHome() {
        executeBlockOnBackground {
            when (val recipes = recipesUseCase.getAllRecipes()) {
                is Result.Success -> {
                    mutableState.value =
                        RecipeHomeState.Success(recipes.value.map { it.toHomeModel() })
                }
                is Result.Failure -> {
                    recipes.errorEntity?.let {
                        mutableState.value = RecipeHomeState.Failure(it)
                    }
                }
            }
        }
    }

    fun getRecipeBySearch(
        query: String,
        isNameOfRecipeFilterSelected: Boolean? = _isNameOfRecipeFilterSelected.value
    ) {
        executeBlockOnBackground {
            isNameOfRecipeFilterSelected?.let { isNameFilterSelected ->
                query.let { searchQuery ->
                    val recipes =
                        recipesUseCase.getRecipesBySearch(searchQuery, isNameFilterSelected)

                    when (recipes) {
                        is Result.Success -> {
                            mutableState.value =
                                RecipeHomeState.Success(recipes.value.map { it.toHomeModel() })
                        }
                        is Result.Failure -> {
                            recipes.errorEntity?.let {
                                mutableState.value = RecipeHomeState.Failure(it)
                            }
                        }
                    }
                }
            }
        }
    }


}