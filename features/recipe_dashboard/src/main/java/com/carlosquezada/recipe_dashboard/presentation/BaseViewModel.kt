package com.carlosquezada.recipe_dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

abstract class BaseViewModel() : ViewModel() {

    protected fun executeBlockOnBackground(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch {
        withContext(dispatcher) {
            block()
        }
    }
}


