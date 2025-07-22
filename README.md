# 🍽️ KMM Meals App

A **Kotlin Multiplatform Mobile (KMM)** application for browsing and viewing meal recipes using [TheMealDB API](https://www.themealdb.com/api.php). Built as a senior engineer assessment to demonstrate cross-platform mobile development with shared business logic and native UI components.

---

## 📑 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [Project Structure](#-project-structure)
- [Usage](#-usage)
- [API Reference](#-api-reference)
- [Roadmap](#-roadmap)
- [Contributing](#-contributing)
- [License](#-license)
- [Author](#-author)

---

## 🚀 Features

- **Meal Categories**: Browse **Seafood** and **Beef** meals.
- **Meal Listings**: Display meal name and thumbnail image.
- **Meal Details**: View full recipe instructions and larger image.
- **UI States**:
  - Loading indicator
  - Success state
  - Error state with retry option
- **Caching**: Local caching of fetched data with SQLDelight for offline support.
- **Dependency Injection**: Shared `Koin` modules across platforms.
- **Networking**: `Ktor` HTTP client in shared module.
- **Native UI**:
  - Android: Jetpack Compose
  - iOS: SwiftUI

## 🛠 Tech Stack

- **Kotlin** (1.9+)
- **Kotlin Multiplatform Mobile**
- **SQLDelight** (SQLite)
- **Koin** (DI)
- **Ktor** (Networking)
- **Jetpack Compose** (Android UI)
- **SwiftUI** (iOS UI)
- **Coroutines & Flow**

## 📋 Prerequisites

- **JDK** 11 or higher
- **Android Studio** Arctic Fox or newer
- **Xcode** 12 or newer
- **CocoaPods** (for iOS)

## 🏁 Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/shakatreh/Meals.git
   cd Meals



2. **Configure dependencies**:

   ```bash
   # Android
   cd composeApp
   ./gradlew clean build

   # iOS
   cd iosApp
   pod install


## 📂 Project Structure

```text
├── shared
│   ├── src
│   │   ├── commonMain
│   │   └── androidMain
│   │   └── iosMain
│   ├── sqldelight            # Database definitions & DAOs
│   └── di                    # Koin modules
├── composeApp                # Android app (Jetpack Compose)
├── iosApp                    # iOS app (SwiftUI)
├── settings.gradle.kts
└── build.gradle.kts
```

## 🎬 Usage

- Launch the app, select **Seafood** or **Beef** tab.
- Scroll through the list of meals.
- Tap on a meal to view detailed instructions.
- Pull-to-refresh or use retry on errors.


## 📦 API Reference
- List Seafood Meals: GET https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
- List Beef Meals:   GET https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef
- Meal Details:      GET https://www.themealdb.com/api/json/v1/1/lookup.php?i={mealId}


## 🛣 Roadmap
- Pagination support
- Unit and UI tests
- Dark mode
- Accessibility improvements

## 🤝 Contributing
Contributions are welcome!  
Please open an issue or submit a pull request.


## 📄 License
This project is licensed under the MIT License.

## 👨‍💻 Author
Belal Shakhatreh