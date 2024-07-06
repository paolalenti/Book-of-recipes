package com.example.bookofrecipes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bookofrecipes.R
import com.example.bookofrecipes.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var binding: FragmentFavoritesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

