package com.coding.meal.food.data.network

import com.coding.meal.core.data.safeCall
import com.coding.meal.core.domain.DataError
import com.coding.meal.core.domain.Result
import com.coding.meal.core.util.FoodType
import com.coding.meal.food.data.dto.MealDetailsDto
import com.coding.meal.food.data.dto.MealDetailsResponseDto
import com.coding.meal.food.data.dto.MealDto
import com.coding.meal.food.data.dto.MealsResponseDto
import com.coding.meal.food.domain.Food
import com.coding.meal.food.domain.FoodDetails
import com.coding.meal.sqldelight.cache.Database
import com.coding.meal.sqldelight.cache.TableKey
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1"

class KtorRemoteFoodDataSource(
    private val httpClient: HttpClient,
    private val database: Database
) : RemoteFoodDataSource {
    override suspend fun getFood(type: FoodType): Result<MealsResponseDto, DataError.Remote> {
        val cachedLaunches = database.getFoods(type)
        return if (cachedLaunches.isNotEmpty()) {
            Result.Success(MealsResponseDto(cachedLaunches.map {
                MealDto(it.name, it.image, it.id)
            }.toList()))
        } else {
            safeCall<MealsResponseDto> {
                httpClient.get(
                    urlString = "$BASE_URL/filter.php?c=${type.value}"
                )
            }.also {
                if (it is Result.Success) {
                    database.insertOrReplaceFoods(type, it.data.meals.map { meal -> Food(meal.idMeal, meal.strMeal, meal.strMealThumb) }.toList())
                }
            }
        }
    }

    override suspend fun getFoodDetails(id: String): Result<MealDetailsResponseDto, DataError.Remote> {
        val cachedLaunches = database.getFoodDetails(id)
        return if (cachedLaunches.isNotEmpty()) {
            Result.Success(MealDetailsResponseDto(cachedLaunches.map {
                MealDetailsDto(it.id, it.name, it.area, it.instruction, it.image)
            }.toList()))
        } else {
            safeCall<MealDetailsResponseDto> {
                httpClient.get(
                    urlString = "$BASE_URL//lookup.php?i=$id"
                )
            }.also {
                if (it is Result.Success) {
                    database.insertOrReplaceFoodDetails(
                        it.data.meals.map { FoodDetails(it.idMeal, it.strMeal, it.strArea, it.strInstructions, it.strMealThumb) }.first())
                }
            }
        }
    }

}