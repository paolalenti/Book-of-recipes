package com.example.bookofrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookofrecipes.R
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.databinding.FragmentRecipeEditorBinding
import com.example.bookofrecipes.recyclers.editor.ingredient.IngredientQuantityFormData
import com.example.bookofrecipes.recyclers.editor.ingredient.IngredientsListAdapter
import com.example.bookofrecipes.recyclers.editor.step.StepsListAdapter
import com.google.android.material.snackbar.Snackbar


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

    private fun validateForm(): Boolean {
        if (binding?.etRecipeName?.text?.isEmpty() != false) {
            view?.let {
                Snackbar.make(
                    it,
                    getString(R.string.editor_name_error),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            return false
        }
        if (ingredientsAdapter.containsNull()) {
            view?.let {
                Snackbar.make(
                    it,
                    getString(R.string.editor_empty_ingredient_error),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            return false
        }
        if (stepsAdapter.containsNull()) {
            view?.let {
                Snackbar.make(
                    it,
                    getString(R.string.editor_empty_step_error),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            return false
        }
        return true
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
            btCreate.setOnClickListener {
                if (validateForm()) {
                    val index = db.recipeDao().insert(
                        Recipe(
                            name = etRecipeName.text.toString(),
                            time = etRecipeTime.text.toString(),
                            description = etRecipeDescription.text.toString()
                        )
                    )[0]
                    ingredientsAdapter.addItemsToDb(index)
                    stepsAdapter.addItemsToDb(index)

                    findNavController().navigate(
                        resId = R.id.action_recipeEditorFragment_to_homeAdvancedFragment,
                        // args = HomeAdvancedFragment.bundle(context, index) // TODO: change index type from Int to Long in HomeAdvancedFragment
                    )
                }
            }
        }
    }
}