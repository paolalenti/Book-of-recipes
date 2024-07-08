package com.example.bookofrecipes.recyclers.editor.ingredient

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.databinding.ItemEditorIngredientBinding

class IngredientHolder(
    private val binding: ItemEditorIngredientBinding,
    private val db: RecipesDatabase,
    private val onNameChanged: (Int, String?) -> Unit,
    private val onQuantityChanged: (Int, String?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentPosition = -1

    fun clear() {
        binding.run {
            tvIngredientName.text = null
            etIngredientQuantity.text = null
        }
    }

    fun onBind(position: Int, init: IngredientQuantityFormData?) {
        if (currentPosition == -1) {
            binding.run {
                tvIngredientName.setText(init?.name)
                etIngredientQuantity.setText(init?.quantity)
                tvIngredientName.doOnTextChanged { text, _, _, _ ->
                    text?.toString()?.let {
                        val ingredients = db.ingredientDao().searchLimited(it, 1)
                        tvIngredientName.completionHint =
                            if (ingredients.isNotEmpty()) ingredients[0].name else ""
                    }
                    onNameChanged(currentPosition, text?.toString())
                }
                etIngredientQuantity.doOnTextChanged { text, _, _, _ ->
                    onQuantityChanged(currentPosition, text?.toString())
                }
            }
        }
        currentPosition = position
    }
}