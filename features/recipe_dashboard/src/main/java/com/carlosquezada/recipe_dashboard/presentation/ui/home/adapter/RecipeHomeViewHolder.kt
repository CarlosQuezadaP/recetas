package com.carlosquezada.recipe_dashboard.presentation.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.carlosquezada.recipe_dashboard.databinding.RecipeItemLayoutBinding
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeHomeModel

class RecipeHomeViewHolder(private val recipeItemLayout: RecipeItemLayoutBinding) :
    RecyclerView.ViewHolder(recipeItemLayout.root) {

    fun onBind(recipeHomeModel: RecipeHomeModel) {
        recipeItemLayout.textRecipeName.text = recipeHomeModel.name
        Glide.with(recipeItemLayout.root.context)
            .load(recipeHomeModel.imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(recipeItemLayout.imageView);
    }

}