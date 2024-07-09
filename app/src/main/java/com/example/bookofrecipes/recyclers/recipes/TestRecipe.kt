package com.example.bookofrecipes.recyclers.recipes

import com.example.bookofrecipes.recyclers.steps.TestRecipeStep

data class TestRecipe(
    val id: Int,
    val name: String,
    val time: String,
    val ingredients: List<String>,
    val steps: List<TestRecipeStep>,
    val url: String
)