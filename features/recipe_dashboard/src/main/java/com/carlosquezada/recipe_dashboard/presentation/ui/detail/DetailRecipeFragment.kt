package com.carlosquezada.recipe_dashboard.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.carlosquezada.recipe_dashboard.R
import com.carlosquezada.recipe_dashboard.databinding.FragmentDetailRecipeBinding
import com.carlosquezada.recipe_dashboard.presentation.activity.DashboardViewModel
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeDetailModel
import com.carlosquezada.recipe_dashboard.presentation.ui.detail.adapter.ItemPreparation
import com.carlosquezada.recipe_dashboard.presentation.ui.detail.adapter.PreparationAdapter
import com.carlosquezada.recipe_dashboard.presentation.ui.map.DialogMap
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRecipeFragment : Fragment(R.layout.fragment_detail_recipe) {

    private val args: DetailRecipeFragmentArgs by navArgs()

    private val dashboardViewModel: DashboardViewModel by activityViewModel()
    private val detailRecipeViewModel: DetailRecipeViewModel by viewModel()

    private var preparationAdapter: PreparationAdapter = PreparationAdapter()

    private lateinit var fragmentDetailDataBinding: FragmentDetailRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailDataBinding =
            FragmentDetailRecipeBinding.inflate(layoutInflater, container, false)
        fragmentDetailDataBinding.viewModelRecipeDetail = detailRecipeViewModel
        return fragmentDetailDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameOfRecipe = args.nameOfRecipe
        val imageUrl = args.imageUrl
        changeToolbarData(nameOfRecipe, imageUrl)
        detailRecipeViewModel.getRecipeByName(nameOfRecipe)
        observeViewModelEvent()
        setupPreparationAdapter()
        fragmentDetailDataBinding.tvLocation.setOnClickListener {
            DialogMap.newInstance(nameOfRecipe, 19.8077463, -99.4077038)
                .show(requireActivity().supportFragmentManager, "TAG_LOCATION");
        }
    }

    private fun observeViewModelEvent() {
        detailRecipeViewModel.recipe.observe(viewLifecycleOwner, ::updateStepsOfPreparation)
    }

    private fun updateStepsOfPreparation(recipes: RecipeDetailModel) {
        val steps = recipes.stepsForPreparation.mapIndexed { index, s ->
            ItemPreparation(s, (index + 1).toString())
        }
        preparationAdapter.submitList(steps)
    }

    private fun setupPreparationAdapter() {
        fragmentDetailDataBinding.recyclerPreparation.adapter = preparationAdapter
    }

    private fun changeToolbarData(nameOfRecipe: String, imageUrl: String) {
        dashboardViewModel.apply {
            setTemporalNameOfToolbar(nameOfRecipe)
            setImageUrlTemporal(imageUrl)
        }
    }

}