package com.coding.meal.food.domain

import com.coding.meal.core.domain.DataError
import com.coding.meal.core.domain.Result
import com.coding.meal.core.util.FoodType

interface FoodRepository {
    suspend fun getFood(type: FoodType): Result<List<Food>, DataError.Remote>
    suspend fun getFoodDetails(id: String): Result<FoodDetails, DataError.Remote>
}