package com.example.bookofrecipes.recyclers.recipes

import com.example.bookofrecipes.recyclers.steps.TestRecipeStep

object RecipeRepository {
    val recipes: List<TestRecipe> = listOf(
        TestRecipe(
            id = 1,
            name = "Ice Cream",
            url = "https://media.vprok.ru/products/x700/hk/ht/ginwt2yazgvko22b6jvrhgayrckmhthk.jpeg",
            time = "2 hours",
            ingredients = listOf("молоко","сливки","сахар","желток"),
            steps = listOf(
                TestRecipeStep("Желтки растираем с сахаром", false),
                TestRecipeStep("Молоко доводим до кипения и снимаем с огня. Аккуратно вводим в молоко желтковую массу, перемешиваем",false),
                TestRecipeStep("Охлажденные сливки взбиваем", false),
                TestRecipeStep("Вводим в сливки остывшую молочно-желтковую массу", false),
                TestRecipeStep( "Выкладываем в формы и отправляем в морозилку" +
                        "Через час достаем, перемешиваем миксером и отправляем обратно до полного застывания", false),
                TestRecipeStep("ура! теперь его можно подавать", false)
            )
        ),
    )
}