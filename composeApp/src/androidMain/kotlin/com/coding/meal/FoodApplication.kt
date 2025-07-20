package com.coding.meal

import android.app.Application
import com.coding.meal.di.initKoin
import org.koin.android.ext.koin.androidContext

class FoodApplication : Application()  {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FoodApplication)
        }
    }
}