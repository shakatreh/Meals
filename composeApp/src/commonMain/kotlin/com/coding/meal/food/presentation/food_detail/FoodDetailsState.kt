package com.coding.meal.food.presentation.food_detail

import com.coding.meal.food.domain.FoodDetails

data class FoodDetailsState(
    val isLoading: Boolean = true,
    val foodDetails: FoodDetails? = null,
    val errorMessage: String = ""
)
