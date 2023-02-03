package com.carlosquezada.recipe_dashboard.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _imageUrlTemporal = MutableLiveData<String>()

    val imageUrlTemporal: LiveData<String>
        get() = _imageUrlTemporal

    fun setImageUrlTemporal(imageUrl: String) {
        _imageUrlTemporal.value = imageUrl
    }

    private val _temporalNameOfRecipe = MutableLiveData<String>()

    val temporalNameOfRecipe: LiveData<String>
        get() = _temporalNameOfRecipe

    fun setTemporalNameOfToolbar(temporalName: String) {
        _temporalNameOfRecipe.value = temporalName
    }

    private val _queryRecipe = MutableLiveData<String>()

    val recipeQuery: LiveData<String>
        get() = _queryRecipe

    fun setQueryRecipe(query: String) {
        _queryRecipe.value = query
    }

    private val _isDashboardPresent = MutableLiveData(true)

    val isDashboardPresent: LiveData<Boolean>
        get() = _isDashboardPresent

    fun setIsDashboardPresent(state: Boolean) {
        _isDashboardPresent.value = state
    }

    private val _isNameOfRecipeFilterSelected = MutableLiveData(true)

    val isNameOfRecipeFilterSelected: LiveData<Boolean>
        get() = _isNameOfRecipeFilterSelected

    fun setIsNameOfRecipeFilterSelected(state: Boolean) {
        _isNameOfRecipeFilterSelected.value = state
    }
}