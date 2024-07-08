package com.example.bookofrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookofrecipes.R
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.databinding.FragmentRecipeEditorBinding
import com.example.bookofrecipes.recyclers.editor.ingredient.IngredientQuantityFormData
import com.example.bookofrecipes.recyclers.editor.ingredient.IngredientsListAdapter
import com.example.bookofrecipes.recyclers.editor.step.StepsListAdapter


class RecipeEditorFragment : Fragment(R.layout.fragment_recipe_editor) {
    private var binding: FragmentRecipeEditorBinding? = null
    private lateinit var db: RecipesDatabase
    private lateinit var ingredientsAdapter: IngredientsListAdapter
    private lateinit var stepsAdapter: StepsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = RecipesDatabase.getInstance(requireContext())
        ingredientsAdapter =
            IngredientsListAdapter(items = arrayListOf<IngredientQuantityFormData>(), db = db)
        stepsAdapter = StepsListAdapter(items = arrayListOf<String?>(), db = db)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipeEditorBinding.bind(view)
        binding?.run {
            rvIngredients.adapter = ingredientsAdapter
            rvIngredients.layoutManager = LinearLayoutManager(context)
            btAddIngredient.setOnClickListener {
                ingredientsAdapter.add()
            }
            rvSteps.layoutManager = LinearLayoutManager(context)
            rvSteps.adapter = stepsAdapter
            btAddStep.setOnClickListener {
                stepsAdapter.add()
            }
        }
    }
}