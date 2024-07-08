package com.example.bookofrecipes.recyclers.editor.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.data.models.IngredientQuantity
import com.example.bookofrecipes.databinding.ItemEditorIngredientBinding
import com.example.bookofrecipes.recyclers.editor.step.StepHolder

class IngredientsListAdapter(
    private var items: ArrayList<IngredientQuantityFormData>, private val db: RecipesDatabase
) : RecyclerView.Adapter<IngredientHolder>() {
    private var view: RecyclerView? = null

    private val inputTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val viewIndex = viewHolder.adapterPosition
            val targetIndex = target.adapterPosition
            items[viewIndex] = items[targetIndex].also { items[targetIndex] = items[viewIndex] }
            notifyItemMoved(viewIndex, targetIndex)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            items.removeAt(viewHolder.adapterPosition)
            notifyItemRemoved(viewHolder.adapterPosition)
            (viewHolder as IngredientHolder).clear()
        }
    })

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        view = recyclerView
        inputTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder =
        IngredientHolder(binding = ItemEditorIngredientBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), db = db, onNameChanged = { it: Int, text: String? ->
            if (it < items.size)
                items[it].name = text.orEmpty()

        }, onQuantityChanged = { it: Int, text: String? ->
            if (it < items.size)
                items[it].quantity = text
        })

    fun add() {
        val newIt = items.size
        items.add(IngredientQuantityFormData())
        notifyItemInserted(newIt)
    }

    fun addItemsToDb(recipeId: Long) {
        items.map {
            val ingredient = db.ingredientDao().getByName(it.name)
            db.ingredientQuantityDao().insert(
                IngredientQuantity(
                    recipeId = recipeId, ingredientId = ingredient.id, quantity = it.quantity
                )
            )
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.onBind(position, items.elementAtOrNull(position))
    }
}