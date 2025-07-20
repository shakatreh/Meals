package com.coding.meal.food.presentation.food_detail

sealed interface FoodDetailsAction {
    data object onBackClicked : FoodDetailsAction
}