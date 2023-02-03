package com.carlosquezada.recipe_dashboard.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.carlosquezada.recipe_dashboard.R
import com.carlosquezada.recipe_dashboard.databinding.FragmentHomeRecipesBinding
import com.carlosquezada.recipe_dashboard.domain.exceptions.RecipeException
import com.carlosquezada.recipe_dashboard.presentation.activity.DashboardViewModel
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeHomeModel
import com.carlosquezada.recipe_dashboard.presentation.ui.home.adapter.HomeRecipeAdapter
import com.carlosquezada.recipe_dashboard.presentation.ui.home.listener.RecipeClickListener
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeRecipesFragment : Fragment(R.layout.fragment_home_recipes), RecipeClickListener {

    private var binding: FragmentHomeRecipesBinding? = null

    private val recipeHomeViewHomeModel: HomeRecipeViewModel by viewModel()
    private val dashboardViewModel: DashboardViewModel by activityViewModel()
    private var homeRecipeAdapter = HomeRecipeAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeRecipesBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeRecipesBinding.bind(view)
        binding?.recyclerViewRecipes?.adapter = homeRecipeAdapter
        observeViewModel()
        getRecipes()
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.setIsDashboardPresent(true)
    }


    override fun onPause() {
        super.onPause()
        dashboardViewModel.setIsDashboardPresent(false)
    }


    private fun getRecipes() {
        lifecycleScope.launch {
            recipeHomeViewHomeModel.getAllRecipeHome()
        }
    }

    private fun observeViewModel() {
        recipeHomeViewHomeModel.apply {
            recipeQuery.observe(viewLifecycleOwner, recipeHomeViewHomeModel::getRecipeBySearch)
        }
        dashboardViewModel.apply {
            recipeQuery.observe(
                viewLifecycleOwner,
                recipeHomeViewHomeModel::setQueryRecipe
            )
            isNameOfRecipeFilterSelected.observe(
                viewLifecycleOwner,
                recipeHomeViewHomeModel::setIsNameOfRecipeFilterSelected
            )
        }

        lifecycleScope.launchWhenCreated {
            recipeHomeViewHomeModel.state.onEach(::handleHomeScreenState).launchIn(this)
        }

    }

    private fun handleHomeScreenState(recipeHomeState: RecipeHomeState) {
        binding?.let { content ->
            when (recipeHomeState) {
                RecipeHomeState.Loading -> {
                    lottieAnimationVisibility(true)
                    content.animationView.setAnimation(R.raw.chef)
                    binding?.let {
                        it.textViewErrorText.isVisible = false
                    }
                }
                is RecipeHomeState.Success -> {
                    lottieAnimationVisibility(false)
                    showRecipes(recipes = recipeHomeState.recipes)
                    binding?.let {
                        it.textViewErrorText.isVisible = false
                    }
                }
                is RecipeHomeState.Failure -> {
                    showRecipes(emptyList())
                    content.animationView.setAnimation(
                        when (recipeHomeState.error) {
                            is RecipeException.EmptyRecipes -> {
                                setErrorMessage(recipeHomeState.error.errorMessage)
                                R.raw.empty
                            }
                            is RecipeException.RecipeNotAvailable -> {
                                setErrorMessage(recipeHomeState.error.errorMessage)
                                R.raw.server_error
                            }
                            is RecipeException.UnknownError -> {
                                setErrorMessage(recipeHomeState.error.errorMessage)
                                R.raw.error
                            }
                            else -> {
                                R.raw.error
                            }
                        }
                    )
                    lottieAnimationVisibility(true)
                }
            }
        }
    }

    private fun setErrorMessage(idString: Int) {
        binding?.textViewErrorText?.let {
            it.text = getString(idString)
            it.isVisible = true
        }
    }

    private fun lottieAnimationVisibility(isLoadingVisible: Boolean) {
        binding?.animationView?.isVisible = isLoadingVisible
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun showRecipes(recipes: List<RecipeHomeModel>) {
        homeRecipeAdapter.submitList(recipes)
    }

    override fun onClick(recipeHomeModel: RecipeHomeModel) {
        val navigateToDetailAction =
            HomeRecipesFragmentDirections.actionHomeRecipesFragmentToDetailRecipeFragment(
                recipeHomeModel.name,
                recipeHomeModel.imageUrl
            )
        findNavController().navigate(navigateToDetailAction)
    }

}