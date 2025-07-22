package com.coding.meal.food.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDetailsDto(
    val idMeal: String,
    val strMeal: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
)
