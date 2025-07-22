package com.coding.meal.core.util

import org.junit.Test
import org.junit.Assert.assertEquals

class FoodTypeTest {
    @Test
    fun `currentFoodType returns SEAFOOD when index is 0`() {
        assertEquals(FoodType.SEAFOOD, FoodType.currentFoodType(0))
    }

    @Test
    fun `currentFoodType returns BEEF when index is not 0`() {
        assertEquals(FoodType.BEEF, FoodType.currentFoodType(1))
        assertEquals(FoodType.BEEF, FoodType.currentFoodType(-1))
    }
}