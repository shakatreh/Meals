package com.coding.meal.food.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String
)