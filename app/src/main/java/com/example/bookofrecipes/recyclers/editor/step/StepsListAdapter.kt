package com.example.bookofrecipes.recyclers.editor.step

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.data.models.RecipeStep
import com.example.bookofrecipes.databinding.ItemEditorStepBinding
import kotlin.math.max
import kotlin.math.min

class StepsListAdapter(private var items: ArrayList<String?>, private val db: RecipesDatabase) :
    RecyclerView.Adapter<StepHolder>() {
    private var view: RecyclerView? = null

    private val inputTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT
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
            val minimumIndex = min(viewIndex, targetIndex)
            val maximumIndex = max(viewIndex, targetIndex)
            for (i in minimumIndex..maximumIndex) {
                (view?.findViewHolderForAdapterPosition(i) as StepHolder?)?.setPosition(i)
            }
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            items.removeAt(position)
            notifyItemRemoved(position)
            (viewHolder as StepHolder).clear()
            for (i in position..<itemCount) {
                (view?.findViewHolderForAdapterPosition(i) as StepHolder?)?.setPosition(i)
            }
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepHolder = StepHolder(
        binding = ItemEditorStepBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onChanged = { it: Int, text: String? ->
            if (it < items.size)
                items[it] = text
        })

    fun add() {
        val newIt = items.size
        items.add(null)
        notifyItemInserted(newIt)
    }

    fun containsNull(): Boolean = items.contains(null)

    fun addItemsToDb(recipeId: Long) {
        var removedCount = 0
        items.mapIndexed { it: Int, text: String? ->
            if (text == null) {
                removedCount++
            } else {
                db.recipeStepDao().insert(
                    RecipeStep(
                        recipeId = recipeId,
                        number = (it - removedCount),
                        content = text
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: StepHolder, position: Int) {
        holder.onBind(position, items.elementAtOrNull(position))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        view = recyclerView
        inputTouchHelper.attachToRecyclerView(recyclerView)
    }

}