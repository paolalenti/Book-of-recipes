package com.example.bookofrecipes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookofrecipes.R
import com.example.bookofrecipes.recyclers.recipes.RecipeAdapter
import com.example.bookofrecipes.databinding.FragmentHomeBinding
import com.example.bookofrecipes.data.db.RecipesDatabase
import java.util.ArrayList

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    private var adapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        initAdapter()
    }

    private fun initAdapter() {
        binding?.run {
            adapter = RecipeAdapter(
                list = ArrayList(RecipesDatabase.getInstance(requireContext()).recipeDao().getAll()),
                glide = Glide.with(this@HomeFragment),
                onClick = {
                    findNavController().navigate(
                        resId = R.id.action_homeFragment_to_homeAdvancedFragment,
                        args = HomeAdvancedFragment.bundle(context, it.id),
                    )
                }
            )
            rvRecipe.adapter = adapter
            rvRecipe.layoutManager = LinearLayoutManager(requireContext())
            btCreateNewRecipe.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_homeFragment_to_recipeEditorFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
