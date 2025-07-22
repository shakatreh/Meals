package com.coding.meal.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.coding.meal.food.presentation.food_detail.FoodDetailsScreenRoot
import com.coding.meal.food.presentation.food_detail.FoodDetailsViewModel
import com.coding.meal.food.presentation.food_list.FoodListScreenRoot
import com.coding.meal.food.presentation.food_list.FoodListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.FoodGraph
        ) {
            navigation<Route.FoodGraph>(
                startDestination = Route.FoodList
            ) {
                composable<Route.FoodList> {
                    val viewModel = koinViewModel<FoodListViewModel>()
                    FoodListScreenRoot(
                        viewModel = viewModel,
                        onFoodClick = {
                            navController.navigate(Route.FoodDetails(it.id))
                        })
                }


                composable<Route.FoodDetails> {
                    val viewModel = koinViewModel<FoodDetailsViewModel>()
                    FoodDetailsScreenRoot(
                        viewModel=viewModel,
                        onBackClick = { navController.popBackStack()}
                    )

                }

            }

        }
    }
}


@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}



