package com.example.bookofrecipes.recyclers.recipes.recyclers.steps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.databinding.ItemStepBinding

class StepAdapter(
    private val steps: List<RecipeStep>
) : RecyclerView.Adapter<StepHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StepHolder = StepHolder(
        binding = ItemStepBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
    )

    override fun onBindViewHolder(holder: StepHolder, position: Int) {
        holder.onBind(steps[position], position)
    }

    override fun getItemCount(): Int = steps.size
}
