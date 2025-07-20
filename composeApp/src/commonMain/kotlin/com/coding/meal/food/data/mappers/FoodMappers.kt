package com.coding.meal.food.data.mappers

import com.coding.meal.food.data.dto.MealDetailsDto
import com.coding.meal.food.data.dto.MealDto
import com.coding.meal.food.domain.Food
import com.coding.meal.food.domain.FoodDetails

fun MealDto.toFood() : Food{
    return Food(
        id = idMeal,
        name = strMeal,
        image = strMealThumb
    )
}

fun MealDetailsDto.toFoodDetails(): FoodDetails{
    return FoodDetails(
        name = strMeal,
        area = strArea,
        instruction =strInstructions ,
        image = strMealThumb
    )
}