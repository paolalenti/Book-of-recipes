package com.example.bookofrecipes.recyclers.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.databinding.ItemRecipeBinding

class RecipeAdapter(
    private var list: ArrayList<Recipe>,
    private val glide: RequestManager,
    private val onClick: (Recipe) -> Unit,
) : RecyclerView.Adapter<RecipeHolder>() {

    private var view: RecyclerView? = null

    private val inputTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            RecipesDatabase.getInstance(view?.context!!).recipeDao().delete(list[viewHolder.adapterPosition])
            list.removeAt(viewHolder.adapterPosition)
            notifyItemRemoved(viewHolder.adapterPosition)
        }
    })

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        view = recyclerView
        inputTouchHelper.attachToRecyclerView(recyclerView)
    }

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