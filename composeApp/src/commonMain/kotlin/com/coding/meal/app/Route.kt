package com.coding.meal.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object FoodGraph : Route

    @Serializable
    data object FoodList : Route

    @Serializable
    data class FoodDetails(val id: String) : Route

}