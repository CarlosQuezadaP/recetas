package com.carlosquezada.recipe_dashboard.presentation.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.carlosquezada.recipe_dashboard.databinding.ItemPreparationBinding

class PreparationViewHolder(private val itemLayoutView: ItemPreparationBinding) :
    RecyclerView.ViewHolder(itemLayoutView.root) {

    fun onBind(itemPreparation: ItemPreparation) {
        itemLayoutView.tvNumber.text = itemPreparation.number
        itemLayoutView.tvStep.text = itemPreparation.step
    }

}