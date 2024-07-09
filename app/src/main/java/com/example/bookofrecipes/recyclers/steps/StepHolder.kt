package com.example.bookofrecipes.recyclers.steps

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.data.models.RecipeStep
import com.example.bookofrecipes.databinding.ItemStepBinding

class StepHolder(
    private val binding: ItemStepBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(step: RecipeStep) {
        binding.tvNumber.text = (step.number + 1).toString()
        binding.tvStep.text = step.content
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            binding.tvStep.apply {
                paintFlags = if (isChecked) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

            }
        }
    }
}
