package com.coding.meal.sqldelight.cache

import com.coding.meal.core.util.FoodType
import com.coding.meal.food.domain.Food
import com.coding.meal.food.domain.FoodDetails
import kotlinx.datetime.Clock

enum class TableKey(val value: String) {
    SEAFOOD("Seafood"),
    BEEF("Beef"),
    FOOD_DETAILS("foodDetails")
}


class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val cacheTablesInfoQueries = database.cacheTablesInfoQueries
    private val beefQueries = database.beefQueries
    private val seaFoodQueries = database.seaFoodQueries
    private val foodDetailsQueries = database.foodDetailsQueries


    private fun ifNeedAnUpdate(table: TableKey, id: String? = null): Boolean {
        val query = when(table) {
            TableKey.SEAFOOD,
            TableKey.BEEF -> cacheTablesInfoQueries.selectCacheTime(table.value)
            TableKey.FOOD_DETAILS -> foodDetailsQueries.selectTop1(id ?: "")
        }
        val result = query.executeAsOneOrNull()
        val now = Clock.System.now().toEpochMilliseconds()
        return result == null || result < now - 1000 * 60 * 60 // 1 hour
    }


    internal fun getFoods(type: FoodType): List<Food> {
        if (ifNeedAnUpdate(if(type == FoodType.BEEF) TableKey.BEEF else TableKey.SEAFOOD)) return emptyList()
        return when (type) {
            FoodType.SEAFOOD -> seaFoodQueries.selectAll(::Food).executeAsList()
            FoodType.BEEF -> beefQueries.selectAll(::Food).executeAsList()
        }
    }

    internal fun insertOrReplaceFoods(type: FoodType, items: List<Food>) {
        when(type) {
            FoodType.SEAFOOD ->
                seaFoodQueries.transaction {
                    items.forEach { food ->
                        seaFoodQueries.replaceFood(
                            id = food.id,
                            name = food.name,
                            image = food.image
                        )
                    }
                }
            FoodType.BEEF ->
                beefQueries.transaction {
                    items.forEach { food ->
                        beefQueries.replaceFood(
                            id = food.id,
                            name = food.name,
                            image = food.image
                        )
                    }
                }

        }
        cacheTablesInfoQueries.replaceCacheTime(type.value, Clock.System.now().toEpochMilliseconds())
    }


    internal fun getFoodDetails(id: String): List<FoodDetails> {
        if (ifNeedAnUpdate(TableKey.FOOD_DETAILS, id)) return emptyList()
        return foodDetailsQueries.selectDetailById(id).executeAsList().map {
            FoodDetails(
                id = it.id,
                name = it.name,
                area = it.area,
                instruction = it.instruction,
            )}
    }

    internal fun insertOrReplaceFoodDetails(foodDetails: FoodDetails) {
        foodDetailsQueries.replaceDetail(id = foodDetails.id, name = foodDetails.name, area = foodDetails.area,
            instruction = foodDetails.instruction, image = foodDetails.image, timestamp = Clock.System.now().toEpochMilliseconds())
    }


}