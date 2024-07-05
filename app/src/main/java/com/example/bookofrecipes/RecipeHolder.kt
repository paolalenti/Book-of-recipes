package com.example.bookofrecipes

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bookofrecipes.databinding.ItemRecipeBinding


class RecipeHolder(
    private val binding: ItemRecipeBinding,
    private val glide: RequestManager,
    private val onClick: (Recipe) -> Unit,
) : ViewHolder(binding.root) {

    private val requestOptions = RequestOptions
        .diskCacheStrategyOf(
            DiskCacheStrategy.ALL
        )

    private val context: Context
        get() = itemView.context

    fun onBind(recipe: Recipe) {
        binding.run {
            tvName.text = recipe.name

            glide
                .load(recipe.url)
                .error(R.drawable.img_not_found)
                .placeholder(R.drawable.img_cat)
                .apply(requestOptions)
                .into(ivImage)

            root.setOnClickListener {
                onClick.invoke(recipe)
            }
        }
    }

}