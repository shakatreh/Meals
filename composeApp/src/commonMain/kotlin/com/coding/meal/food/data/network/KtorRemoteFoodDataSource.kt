package com.coding.meal.food.data.network

import com.coding.meal.core.data.safeCall
import com.coding.meal.core.domain.DataError
import com.coding.meal.core.domain.Result
import com.coding.meal.food.data.dto.MealDetailsResponseDto
import com.coding.meal.food.data.dto.MealsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1"

class KtorRemoteFoodDataSource(
    private val httpClient: HttpClient
) : RemoteFoodDataSource {
    override suspend fun getFood(type: String): Result<MealsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/filter.php?c=$type"
            )
        }
    }

    override suspend fun getFoodDetails(id: String): Result<MealDetailsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL//lookup.php?i=$id"
            )
        }
    }

}