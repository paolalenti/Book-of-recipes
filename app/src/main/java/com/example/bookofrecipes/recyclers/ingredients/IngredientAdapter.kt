package com.example.bookofrecipes.recyclers.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.databinding.ItemIngredientBinding

class IngredientAdapter(
    private val ingredients: List<String>
) : RecyclerView.Adapter<IngredientHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientHolder = IngredientHolder(
        binding = ItemIngredientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
    )

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.onBind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size
}