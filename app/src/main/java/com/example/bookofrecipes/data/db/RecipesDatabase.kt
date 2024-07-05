package com.example.bookofrecipes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookofrecipes.data.dao.IngredientDao
import com.example.bookofrecipes.data.dao.IngredientQuantityDao
import com.example.bookofrecipes.data.dao.RecipeDao
import com.example.bookofrecipes.data.dao.RecipeStepDao
import com.example.bookofrecipes.data.models.Ingredient
import com.example.bookofrecipes.data.models.IngredientQuantity
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.data.models.RecipeStep

@Database(
    entities = [Recipe::class, RecipeStep::class, IngredientQuantity::class, Ingredient::class],
    version = 1
)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeStepDao(): RecipeStepDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun ingredientQuantityDao(): IngredientQuantityDao
}