package com.coding.meal.di

import com.coding.meal.sqldelight.cache.DatabaseDriverFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module
import sqldelight.cache.IOSDatabaseDriverFactory

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single<DatabaseDriverFactory> { IOSDatabaseDriverFactory() }
    }