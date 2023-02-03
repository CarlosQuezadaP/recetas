package com.carlosquezada.recipe_dashboard.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.carlosquezada.recipe_dashboard.databinding.RecipeItemLayoutBinding
import com.carlosquezada.recipe_dashboard.presentation.models.RecipeHomeModel
import com.carlosquezada.recipe_dashboard.presentation.ui.home.listener.RecipeClickListener

class HomeRecipeAdapter(private val recipeClickListener: RecipeClickListener) :
    ListAdapter<RecipeHomeModel, RecipeHomeViewHolder>(RecipeHomeModelDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHomeViewHolder {
        val binding =
            RecipeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeHomeViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            recipeClickListener.onClick(getItem(position))
        }
    }

    private class RecipeHomeModelDiffCallback : DiffUtil.ItemCallback<RecipeHomeModel>() {

        override fun areItemsTheSame(oldItem: RecipeHomeModel, newItem: RecipeHomeModel) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: RecipeHomeModel, newItem: RecipeHomeModel) =
            oldItem == newItem
    }


}
