package com.coding.meal

import androidx.compose.ui.window.ComposeUIViewController
import com.coding.meal.app.App
import com.coding.meal.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = { initKoin() }
){ App() }