package com.example.bookofrecipes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookofrecipes.data.dao.IngredientDao
import com.example.bookofrecipes.data.dao.IngredientQuantityDao
import com.example.bookofrecipes.data.dao.RecipeDao
import com.example.bookofrecipes.data.dao.RecipeStepDao
import com.example.bookofrecipes.data.db.RecipesDatabase
import com.example.bookofrecipes.data.models.Ingredient
import com.example.bookofrecipes.data.models.IngredientQuantity
import com.example.bookofrecipes.data.models.Recipe
import com.example.bookofrecipes.data.models.RecipeStep
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTests {
    private lateinit var recipeDao: RecipeDao
    private lateinit var recipeStepDao: RecipeStepDao
    private lateinit var ingredientDao: IngredientDao
    private lateinit var ingredientQuantityDao: IngredientQuantityDao
    private lateinit var database: RecipesDatabase
    private val recipeName = "AWESOME new recipe"
    private val ingredientDescription = "Hello world"
    private val otherRecipeName = "other recipe"
    private val ingredientName = "Green apple"
    private val otherIngredientName ="Pineapple"

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, RecipesDatabase::class.java).build()
        recipeDao = database.recipeDao()
        recipeStepDao = database.recipeStepDao()
        ingredientDao = database.ingredientDao()
        ingredientQuantityDao = database.ingredientQuantityDao()

        val recipe = Recipe(name = recipeName, description = ingredientDescription)
        val otherRecipe = Recipe(name = otherRecipeName)
        val ingredientApple = Ingredient(name = ingredientName)
        val ingredientPineapple = Ingredient(name = otherIngredientName)
        recipeDao.insert(recipe, otherRecipe)
        ingredientDao.insert(ingredientApple, ingredientPineapple)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsertGet() {
        val recipes = recipeDao.getAll()
        assert(recipes.size == 2)
        val recipeId = recipes[0].id
        val secondRecipeId = recipes[1].id
        val recipeFromDb = recipeDao.getById(recipeId)
        assert(recipeFromDb != null)
        assert(recipeFromDb?.name == recipeName)

        recipeStepDao.insert(RecipeStep(recipeId = recipeId, content = "Wash some dishes", number = 0))
        val recipeStep = recipeStepDao.getAllForRecipe(recipeFromDb!!)
        assert(recipeStep.isNotEmpty())

        val ingredients = ingredientDao.getAll()
        assert(ingredients.size == 2)
        val ingredientId = ingredients[0].id
        val secondIngredientId = ingredients[1].id
        val ingredientFromDb = ingredientDao.getById(ingredientId)
        assert(ingredientFromDb != null)
        assert(ingredientFromDb?.name == ingredientName)

        ingredientQuantityDao.insert(IngredientQuantity(recipeId = recipeId, ingredientId = ingredientId, quantity = "4 kg"))
        ingredientQuantityDao.insert(IngredientQuantity(recipeId = recipeId, ingredientId = secondIngredientId, quantity = "2 kg"))
        ingredientQuantityDao.insert(IngredientQuantity(recipeId = secondRecipeId, ingredientId = secondIngredientId))

        val ingredientRelationsByIngredient = ingredientQuantityDao.getAllByIngredientId(ingredientId)
        assert(ingredientRelationsByIngredient.size == 1)
        val ingredientRelationsByRecipe = ingredientQuantityDao.getAllByRecipeId(recipeId)
        assert(ingredientRelationsByRecipe.size == 2)
        val ingredientAnyFilter = recipeDao.filterByAnyIngredients(listOf(ingredientId, secondIngredientId))
        assert(ingredientAnyFilter.size == 2)
        val recipeIngredientsFilter = recipeDao.filterByIngredients(listOf(ingredientId, secondIngredientId))
        assert(recipeIngredientsFilter.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun testSearch() {
        val recipes = recipeDao.searchAll("Hello")
        assert(recipes.isNotEmpty())
        val recipesNoMatch = recipeDao.searchAll("C2H5OH")
        assert(recipesNoMatch.isEmpty())
        val ingredients = ingredientDao.searchAll("Apple")
        assert(ingredients.isNotEmpty())
        val ingredientsNoMatch = ingredientDao.searchAll("iron")
        assert(ingredientsNoMatch.isEmpty())
    }
}