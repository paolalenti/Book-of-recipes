package com.example.bookofrecipes.recyclers.recipes

import com.example.bookofrecipes.recyclers.recipes.recyclers.steps.RecipeStep

data class Recipe(
    val id: Int,
    val name: String,
    val time: String,
    val ingredients: List<RecipeStep>,
    val steps: List<String>,
    val url: String
)