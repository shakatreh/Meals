package com.coding.meal.food.data.repository

import com.coding.meal.core.domain.DataError
import com.coding.meal.core.domain.Result
import com.coding.meal.core.domain.map
import com.coding.meal.food.data.mappers.toFood
import com.coding.meal.food.data.mappers.toFoodDetails
import com.coding.meal.food.data.network.RemoteFoodDataSource
import com.coding.meal.food.domain.Food
import com.coding.meal.food.domain.FoodDetails
import com.coding.meal.food.domain.FoodRepository

class DefaultFoodRepository(
    private val remoteFoodDataSource: RemoteFoodDataSource
) : FoodRepository {
    override suspend fun getFood(type: String): Result<List<Food>, DataError.Remote> {
        return remoteFoodDataSource.getFood(type)
            .map { dto ->
                dto.meals.map {
                    it.toFood()
                }
            }
    }

    override suspend fun getFoodDetails(id: String): Result<FoodDetails, DataError.Remote> {
        return remoteFoodDataSource.getFoodDetails(id).map { dto->
            dto.meals.first().toFoodDetails()
        }
    }

}