package com.carlosquezada.recipe_dashboard.presentation.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.carlosquezada.recipe_dashboard.databinding.ItemPreparationBinding

class PreparationAdapter() :
    ListAdapter<ItemPreparation, PreparationViewHolder>(DetailDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreparationViewHolder {
        val binding =
            ItemPreparationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PreparationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreparationViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class DetailDiffCallback : DiffUtil.ItemCallback<ItemPreparation>() {

        override fun areItemsTheSame(oldItem: ItemPreparation, newItem: ItemPreparation) =
            oldItem.number == newItem.number

        override fun areContentsTheSame(oldItem: ItemPreparation, newItem: ItemPreparation) =
            oldItem == newItem
    }


}