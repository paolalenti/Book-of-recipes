package com.example.bookofrecipes.recyclers.editor.step

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bookofrecipes.R
import com.example.bookofrecipes.databinding.ItemEditorStepBinding

class StepHolder(
    private val binding: ItemEditorStepBinding,
    private val onChanged: (position: Int, text: String?) -> Unit
) : ViewHolder(binding.root) {
    private var currentPosition = -1

    fun clear() {
        binding.run {
            etStepDescription.text = null
        }
    }

    fun setPosition(position: Int) {
        binding.run {
            tvNumber.text =
                binding.root.context.getString(R.string.editor_step_number_format, position + 1)
        }
        currentPosition = position
    }

    fun onBind(position: Int, initValue: String?) {
        binding.run {
            tvNumber.text =
                binding.root.context.getString(R.string.editor_step_number_format, position + 1)
            etStepDescription.text = null
        }
        if (currentPosition == -1) {
            binding.run {
                etStepDescription.setText(initValue)
                etStepDescription.doOnTextChanged { text, _, _, _ ->
                    onChanged(currentPosition, text?.toString())
                }
            }
        }
        currentPosition = position
    }
}