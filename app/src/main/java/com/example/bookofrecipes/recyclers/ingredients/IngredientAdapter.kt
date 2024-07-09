package com.example.bookofrecipes.recyclers.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.R
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.data.models.IngredientQuantity
import com.example.bookofrecipes.databinding.ItemIngredientBinding

class IngredientAdapter(
    private val ingredients: List<IngredientQuantity>,
    private val db: RecipesDatabase
) : RecyclerView.Adapter<IngredientHolder>() {

    private var binding: ItemIngredientBinding? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientHolder {
        binding = ItemIngredientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IngredientHolder(
            binding = binding!!
        )
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        binding?.run {
            holder.onBind(
                root.context.getString(
                    R.string.info_quantity_format,
                    db.ingredientDao().getById(ingredients[position].ingredientId)?.name,
                    ingredients[position].quantity.orEmpty()
                )
            )
        }
    }

    override fun getItemCount(): Int = ingredients.size
}