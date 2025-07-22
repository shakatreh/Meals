# 🍽️ KMM Meals App

A Kotlin Multiplatform Mobile (KMM) application built using **Compose Multiplatform** with **shared UI**, targeting both **Android** and **iOS**. This app fetches and displays meals from [TheMealDB API](https://www.themealdb.com/api.php) and is part of a senior engineer assignment.

## 📱 Features

### 🧾 Meals Listing
- Two meal categories: **Seafood** and **Beef**
- Displays:
  - Meal name
  - Thumbnail image
- Handled UI states:
  - Loading
  - Success
  - Error (with retry)

### 🍛 Meal Details
- Shows:
  - Meal name
  - Area (cuisine)
  - Instructions (scrollable)
  - Full-size image

## 🛠️ Tech Stack

| Layer            | Technology                  |
|------------------|-----------------------------|
| UI               | Compose Multiplatform       |
| Architecture     | MVVM + Clean Architecture   |
| Networking       | Ktor Client (commonMain)    |
| State Management | Kotlin StateFlow            |
| DI               | Koin (shared + platforms)   |
| Serialization    | kotlinx.serialization       |

## 📁 Folder Structure

Meal/
├── commonMain/ # Shared KMM module
│ ├── data/ # API, DTOs, repository implementations
│ ├── domain/ # Models, UseCases, repository interfaces
│ ├── presentation/ # ViewModels, Composables
│ ├── di/ # Koin modules
│ └── utils/ # Common utils
├── androidApp/ # Android-specific code
│ └── MainActivity.kt # Hosts Compose shared UI
├── iosApp/ # iOS-specific code
│ └── iOSApp.swift # Hosts Compose UI controller




## 🚀 Getting Started

### ✅ Prerequisites

- Android Studio (Giraffe+)
- Xcode 14+
- macOS with Kotlin Multiplatform & Compose Multiplatform support

### Run on Android

1. Open the project in Android Studio
2. Select `androidApp` configuration
3. Click **Run**

### 🍏 Run on iOS

1. Open `iosApp/iosApp.xcworkspace` in Xcode
2. Set a simulator (e.g., iPhone 14)
3. Run the app (⌘ + R)

> Ensure CocoaPods is installed and run `pod install` in `iosApp` before launching

## 🖼️ Screenshots

(Add screenshots here if available)

## 🎯 Bonus Implemented

- [ ] Local caching via SQLDelight
- [ ] Pagination
- [ ] Unit tests
- [ ] Dark mode support
- [ ] Accessibility improvements

## 📦 API Reference

- [List Seafood Meals](https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood)
- [List Beef Meals](https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef)
- [Meal Details](https://www.themealdb.com/api/json/v1/1/lookup.php?i={mealId})

## 👨‍💻 Author

Belal Shakhatreh
