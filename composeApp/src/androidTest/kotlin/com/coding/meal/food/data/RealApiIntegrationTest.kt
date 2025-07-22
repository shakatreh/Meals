package com.coding.meal.food.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.coding.meal.food.data.network.KtorRemoteFoodDataSource
import com.coding.meal.core.util.FoodType
import com.coding.meal.di.platformModule
import com.coding.meal.di.sharedModule
import com.coding.meal.sqldelight.cache.Database
import com.coding.meal.sqldelight.cache.DatabaseDriverFactory
import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin

@RunWith(AndroidJUnit4::class)
class RealApiIntegrationTest {

    private lateinit var dataSource: KtorRemoteFoodDataSource

    @Before
    fun setup() {
        stopKoin()
        // Context for SQDelight driver
        val context = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext

        val koinApp = startKoin {
            androidContext(context)
            modules(listOf(sharedModule, platformModule))
        }

        // inject required dependencies
        val koin = koinApp.koin
        val engineFactory: DatabaseDriverFactory = koin.get()
        val httpClient: HttpClient = koin.get()
        val database = Database(engineFactory)
        dataSource = KtorRemoteFoodDataSource(httpClient, database)
    }

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun getFood_seaFood_returnsNonEmptyList() = runBlocking {
        val result = dataSource.getFood(FoodType.SEAFOOD)
        // should be a success
        assertTrue(result is com.coding.meal.core.domain.Result.Success)
        // and at least one item
        val respnse = (result as com.coding.meal.core.domain.Result.Success).data
        assertTrue(respnse.meals.isNotEmpty())
    }

    @Test
    fun getFood_beef_returnsNonEmptyList() = runBlocking {
        val result = dataSource.getFood(FoodType.BEEF)
        // should be a success
        assertTrue(result is com.coding.meal.core.domain.Result.Success)
        // and at least one item
        val respnse = (result as com.coding.meal.core.domain.Result.Success).data
        assertTrue(respnse.meals.isNotEmpty())
    }

    @Test
    fun getFoodDetails_returnsNonEmptyList() = runBlocking {
        val result = dataSource.getFoodDetails("52959")
        // should be a success
        assertTrue(result is com.coding.meal.core.domain.Result.Success)
        // and at least one item
        val respnse = (result as com.coding.meal.core.domain.Result.Success).data
        assertTrue(respnse.meals.isNotEmpty())
    }
}
