package com.coding.meal.food.presentation.food_list

import com.coding.meal.food.domain.Food

data class FoodListState(
    val seafoodList: List<Food> = emptyList(),
    val beefList: List<Food> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val selectedTabIndex: Int = 0,
)


