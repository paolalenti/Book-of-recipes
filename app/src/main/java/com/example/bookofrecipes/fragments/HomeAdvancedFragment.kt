package com.example.bookofrecipes.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bookofrecipes.R
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.databinding.FragmentHomeAdvancedBinding
import com.example.bookofrecipes.recyclers.ingredients.IngredientAdapter
import com.example.bookofrecipes.recyclers.steps.StepAdapter

class HomeAdvancedFragment : Fragment(R.layout.fragment_home_advanced) {
    private var binding: FragmentHomeAdvancedBinding? = null
    private val requestOptions = RequestOptions.diskCacheStrategyOf(
        DiskCacheStrategy.ALL
    )
    private var glide: RequestManager? = null
    private lateinit var db: RecipesDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeAdvancedBinding.bind(view)
        db = RecipesDatabase.getInstance(requireContext())
        val recipeId = arguments?.getLong("RECIPE_ID") ?: return
        var recipe = db.recipeDao().getById(recipeId)
        val ingredients = db.ingredientQuantityDao().getAllByRecipeId(recipeId)
        val steps = db.recipeStepDao().getAllByRecipeId(recipeId)
        steps.sortedBy { it.number }
        glide = Glide.with(this@HomeAdvancedFragment)
        binding?.run {
            tvName.text = recipe?.name
            tvTime.text = recipe?.time
            tvDescription.text = recipe?.description
            fabStar.isChecked = recipe?.favorite == true

            glide?.load(recipe?.image)
                ?.error(R.drawable.img_not_found)
                ?.placeholder(R.drawable.img_not_found)?.apply(requestOptions)
                ?.into(ivAboutImage)

            rvIngredient.layoutManager = LinearLayoutManager(context)
            rvIngredient.adapter = IngredientAdapter(ingredients, db)

            rvStep.layoutManager = LinearLayoutManager(context)
            rvStep.adapter = StepAdapter(steps)

            fabGoBack.setOnClickListener {
                findNavController().navigateUp()
            }

            fabStar.setOnCheckedChangeListener { _, isChecked ->
                if (recipe != null) {
                    recipe.favorite = isChecked
                    db.recipeDao().update(recipe)
                }
            }
        }
    }

    companion object {
        fun bundle(context: Context?, recipeId: Long): Bundle {
            val bundle = Bundle()
            bundle.putLong("RECIPE_ID", recipeId)
            return bundle
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}