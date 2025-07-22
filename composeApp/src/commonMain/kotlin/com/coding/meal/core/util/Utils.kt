package com.coding.meal.core.util

enum class FoodType(val value: String) {
    SEAFOOD("Seafood"),
    BEEF("Beef");

    companion object {
        fun currentFoodType(selectedTabIndex: Int): FoodType =
            if (selectedTabIndex == 0) SEAFOOD else BEEF
    }
}

