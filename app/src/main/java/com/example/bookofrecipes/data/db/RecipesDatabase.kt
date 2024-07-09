package com.example.bookofrecipes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookofrecipes.data.dao.IngredientDao
import com.example.bookofrecipes.data.dao.IngredientQuantityDao
import com.example.bookofrecipes.data.dao.RecipeDao
import com.example.bookofrecipes.data.dao.RecipeStepDao
import com.example.bookofrecipes.data.models.Ingredient
import com.example.bookofrecipes.data.models.IngredientFts
import com.example.bookofrecipes.data.models.IngredientQuantity
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.data.models.RecipeFts
import com.example.bookofrecipes.data.models.RecipeStep

/**
 * Application's local database handler
 *
 */
@Database(
    entities = [Recipe::class, RecipeStep::class, IngredientQuantity::class, Ingredient::class, IngredientFts::class, RecipeFts::class],
    version = 2,
    exportSchema = false
)
abstract class RecipesDatabase : RoomDatabase() {
    /**
     * Get recipes data access object
     *
     * @return Data access object
     */
    abstract fun recipeDao(): RecipeDao
    /**
     * Get recipe steps data access object
     *
     * @return Data access object
     */
    abstract fun recipeStepDao(): RecipeStepDao
    /**
     * Get ingredients data access object
     *
     * @return Data access object
     */
    abstract fun ingredientDao(): IngredientDao
    /**
     * Get ingredient quantities data access object
     *
     * @return Data access object
     */
    abstract fun ingredientQuantityDao(): IngredientQuantityDao

    /**
     * Singleton methods
     */
    companion object {
        @Volatile
        private var instance: RecipesDatabase? = null

        /**
         * Get singleton instance for given context
         *
         * @param context Android context
         * @return RecipesDatabase instance
         */
        fun getInstance(context: Context): RecipesDatabase = instance ?: synchronized(this) {
            instance ?: build(context).also { instance = it }
        }

        private fun build(context: Context): RecipesDatabase =
            Room.databaseBuilder(context, RecipesDatabase::class.java, "recipes_db").allowMainThreadQueries().build()
    }
}