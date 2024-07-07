package com.example.bookofrecipes.recyclers.recipes

import com.example.bookofrecipes.recyclers.recipes.recyclers.steps.RecipeStep

object RecipeRepository {
    val recipes: List<Recipe> = listOf(
        Recipe(
            id = 1,
            name = "Ice Cream",
            url = "https://media.vprok.ru/products/x700/hk/ht/ginwt2yazgvko22b6jvrhgayrckmhthk.jpeg",
            time = "2 hours",
            ingredients = listOf("молоко","сливки","сахар","желток"),
            steps = listOf(
                RecipeStep("Желтки растираем с сахаром", false),
                RecipeStep("Молоко доводим до кипения и снимаем с огня. Аккуратно вводим в молоко желтковую массу, перемешиваем",false),
                RecipeStep("Охлажденные сливки взбиваем", false),
                RecipeStep("Вводим в сливки остывшую молочно-желтковую массу", false),
                RecipeStep( "Выкладываем в формы и отправляем в морозилку" +
                        "Через час достаем, перемешиваем миксером и отправляем обратно до полного застывания", false),
                RecipeStep("ура! теперь его можно подавать", false))
        ),
    )
}