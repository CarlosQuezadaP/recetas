package com.carlosquezada.recipe_dashboard.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModelWithState<ViewState>(initialState: ViewState) : BaseViewModel() {
    val mutableState = MutableStateFlow(initialState)
    val state: Flow<ViewState> get() = mutableState
}