package com.coding.meal.food.presentation.food_list

import com.coding.meal.food.domain.Food

sealed interface FoodListAction {
    data class OnFoodClick(val food: Food): FoodListAction
    data class OnTabSelected(val index: Int): FoodListAction
}