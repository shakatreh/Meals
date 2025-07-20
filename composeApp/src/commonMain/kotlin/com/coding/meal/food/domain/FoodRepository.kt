package com.coding.meal.food.domain

import com.coding.meal.core.domain.DataError
import com.coding.meal.core.domain.Result

interface FoodRepository {
    suspend fun getFood(type: String): Result<List<Food>, DataError.Remote>
    suspend fun getFoodDetails(id: String): Result<FoodDetails, DataError.Remote>
}