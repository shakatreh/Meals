package com.coding.meal.food.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealsResponseDto(
    val meals: List<MealDto>
)