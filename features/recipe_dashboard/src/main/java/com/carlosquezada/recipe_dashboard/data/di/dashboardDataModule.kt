package com.carlosquezada.recipe_dashboard.data.di

import com.carlosquezada.recipe_dashboard.data.RecipeLocalDataSource
import com.carlosquezada.recipe_dashboard.data.RecipeRemoteDataSource
import com.carlosquezada.recipe_dashboard.data.RecipeRepository
import com.carlosquezada.recipe_dashboard.data.api.RecipeApi
import com.carlosquezada.recipe_dashboard.domain.RecipesUseCase
import com.carlosquezada.recipe_dashboard.presentation.activity.DashboardViewModel
import com.carlosquezada.recipe_dashboard.presentation.ui.detail.DetailRecipeViewModel
import com.carlosquezada.recipe_dashboard.presentation.ui.home.HomeRecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dashBoardModule = module {
    singleOf(::RecipeApi)
    singleOf(::RecipeRemoteDataSource)
    singleOf(::RecipeLocalDataSource)
    singleOf(::RecipeRepository)
    singleOf(::RecipesUseCase)
    viewModelOf(::HomeRecipeViewModel)
    viewModelOf(::DashboardViewModel)
    viewModelOf(::DetailRecipeViewModel)
}
