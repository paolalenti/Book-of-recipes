package com.example.bookofrecipes.recyclers.steps

import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.databinding.ItemStepBinding

class StepHolder(
    private val binding: ItemStepBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(step: RecipeStep, position: Int) {
        binding.tvNumber.text = "${position + 1}"
        binding.tvStep.text = step.description
        binding.checkBox.isChecked = step.isCompleted
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            step.isCompleted = isChecked
        }
    }
}