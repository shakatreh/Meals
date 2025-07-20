package com.coding.meal.food.data.network

import com.coding.meal.core.domain.DataError
import com.coding.meal.core.domain.Result
import com.coding.meal.food.data.dto.MealDetailsDto
import com.coding.meal.food.data.dto.MealDetailsResponseDto
import com.coding.meal.food.data.dto.MealsResponseDto

interface RemoteFoodDataSource {

    suspend fun getFood(
        type: String
    ): Result<MealsResponseDto, DataError.Remote>

    suspend fun getFoodDetails(id: String): Result<MealDetailsResponseDto, DataError.Remote>
}