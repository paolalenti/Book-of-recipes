package com.example.bookofrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookofrecipes.R
import com.example.bookofrecipes.databinding.FragmentRecipeEditorBinding


class RecipeEditorFragment : Fragment(R.layout.fragment_recipe_editor) {
    private var binding: FragmentRecipeEditorBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipeEditorBinding.bind(view)
    }
}