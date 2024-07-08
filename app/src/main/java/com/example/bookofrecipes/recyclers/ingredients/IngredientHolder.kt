package com.example.bookofrecipes.recyclers.ingredients

import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.databinding.ItemIngredientBinding

class IngredientHolder(
    private val binding: ItemIngredientBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(ingredient: String) {
        binding.tvName.text = ingredient
    }
}