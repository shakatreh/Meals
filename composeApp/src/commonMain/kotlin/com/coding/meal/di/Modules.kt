package com.coding.meal.di

import com.coding.meal.core.data.HttpClientFactory
import com.coding.meal.food.data.network.KtorRemoteFoodDataSource
import com.coding.meal.food.data.network.RemoteFoodDataSource
import com.coding.meal.food.data.repository.DefaultFoodRepository
import com.coding.meal.food.domain.FoodRepository
import com.coding.meal.food.presentation.food_detail.FoodDetailsViewModel
import com.coding.meal.food.presentation.food_list.FoodListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteFoodDataSource).bind<RemoteFoodDataSource>()
    singleOf(::DefaultFoodRepository).bind<FoodRepository>()
    viewModelOf(::FoodListViewModel)

    viewModelOf(::FoodDetailsViewModel)

}