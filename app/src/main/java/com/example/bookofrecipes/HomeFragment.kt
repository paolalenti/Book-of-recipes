package com.example.bookofrecipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookofrecipes.R
import com.example.bookofrecipes.databinding.FragmentHomeBinding

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
                list = RecipeRepository.recipes,
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

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
