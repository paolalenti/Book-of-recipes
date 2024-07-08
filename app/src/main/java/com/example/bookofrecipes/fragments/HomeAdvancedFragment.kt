package com.example.bookofrecipes.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bookofrecipes.R
import com.example.bookofrecipes.databinding.FragmentHomeAdvancedBinding
import com.example.bookofrecipes.recyclers.recipes.RecipeRepository
import com.example.bookofrecipes.recyclers.recipes.recyclers.ingredients.IngredientAdapter
import com.example.bookofrecipes.recyclers.steps.StepAdapter

class HomeAdvancedFragment : Fragment(R.layout.fragment_home_advanced) {
    private var binding: FragmentHomeAdvancedBinding? = null
    private val requestOptions = RequestOptions.diskCacheStrategyOf(
        DiskCacheStrategy.ALL
    )
    private var glide: RequestManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeAdvancedBinding.bind(view)
        val recipeId = arguments?.getInt("RECIPE_ID") ?: return
        val recipe = RecipeRepository.recipes.find { it.id == recipeId } ?: return
        glide = Glide.with(this@HomeAdvancedFragment)
        binding?.run {
            tvName.text = recipe.name
            tvTime.text = recipe.time

            glide?.load(recipe.url)
                ?.error(R.drawable.img_not_found)
                ?.placeholder(R.drawable.img_cat)?.apply(requestOptions)
                ?.into(ivAboutImage)

            rvIngredient.layoutManager = LinearLayoutManager(context)
            rvIngredient.adapter = IngredientAdapter(recipe.ingredients)

            rvStep.layoutManager = LinearLayoutManager(context)
            rvStep.adapter = StepAdapter(recipe.steps)

            fabGoBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    companion object {
        fun bundle(context: Context?, recipeId: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt("RECIPE_ID", recipeId)
            return bundle
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}