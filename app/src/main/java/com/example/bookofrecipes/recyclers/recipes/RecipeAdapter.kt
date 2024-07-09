package com.example.bookofrecipes.recyclers.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.databinding.ItemRecipeBinding

class RecipeAdapter(
    private var list: List<Recipe>,
    private val glide: RequestManager,
    private val onClick: (Recipe) -> Unit,
) : RecyclerView.Adapter<RecipeHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeHolder = RecipeHolder(
        binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onClick = onClick,
    )

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}