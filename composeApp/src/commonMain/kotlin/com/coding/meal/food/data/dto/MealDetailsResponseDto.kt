package com.coding.meal.food.data.dto
import kotlinx.serialization.Serializable

@Serializable
data class MealDetailsResponseDto(
    val meals: List<MealDetailsDto>
)

