package com.example.bookofrecipes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookofrecipes.R
import com.example.bookofrecipes.recyclers.recipes.RecipeAdapter
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.databinding.FragmentFavoritesBinding
import java.util.ArrayList

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var binding: FragmentFavoritesBinding? = null

    private var adapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)
        initAdapter()
    }

    private fun initAdapter() {
        binding?.run {
            adapter = RecipeAdapter(
                list = ArrayList(RecipesDatabase.getInstance(requireContext()).recipeDao().getAll()),
                glide = Glide.with(this@FavoritesFragment),
                onClick = {
                    findNavController().navigate(
                        resId = R.id.action_favoritesFragment_to_homeAdvancedFragment,
                        args = HomeAdvancedFragment.bundle(context, it.id),
                    )
                }
            )
            rvRecipe.adapter = adapter
            rvRecipe.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}