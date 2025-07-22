package com.coding.meal.di

import android.content.Context
import com.coding.meal.sqldelight.cache.DatabaseDriverFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module
import sqldelight.cache.AndroidDatabaseDriverFactory

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(get<Context>()) }
    }
