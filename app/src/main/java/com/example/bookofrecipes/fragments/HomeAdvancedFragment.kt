package com.example.bookofrecipes.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bookofrecipes.R
import com.example.bookofrecipes.databinding.FragmentHomeAdvancedBinding
import com.example.bookofrecipes.recyclers.recipes.RecipeRepository

class HomeAdvancedFragment : Fragment(R.layout.fragment_home_advanced) {
    private var binding: FragmentHomeAdvancedBinding? = null
    private val requestOptions = RequestOptions.diskCacheStrategyOf(
        DiskCacheStrategy.ALL
    )
    private var glide: RequestManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeAdvancedBinding.bind(view)
        glide = Glide.with(this@HomeAdvancedFragment)
        binding?.run {
            val url = arguments?.getString(ARG_URL)
            glide?.load(url)
                ?.error(R.drawable.img_not_found)
                ?.placeholder(R.drawable.img_cat)?.apply(requestOptions)
                ?.into(ivAboutImage)
            tvName.text = arguments?.getString(ARG_NAME)

            fabGoBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    companion object {
        private const val ARG_URL = "URL"
        private const val ARG_NAME = "NAME"
        fun bundle(context: Context?, indx: Int): Bundle = Bundle().apply {
            val recipe = RecipeRepository.recipes.find {it.id == indx}
            if (recipe != null) {
                putString(ARG_URL, recipe.url)
                putString(ARG_NAME, recipe.name)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}