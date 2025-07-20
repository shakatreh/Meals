package com.coding.meal.food.presentation.food_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.coding.meal.app.Route
import com.coding.meal.core.domain.onError
import com.coding.meal.core.domain.onSuccess
import com.coding.meal.food.domain.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FoodDetailsViewModel(
    private val foodRepository: FoodRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.toRoute<Route.FoodDetails>().id

    private val _state = MutableStateFlow(FoodDetailsState())
    val state = _state.asStateFlow()

    init {
        getFoodDetails()
    }

    private fun getFoodDetails() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            foodRepository.getFoodDetails(id)
                .onSuccess { data ->
                    _state.update {
                        it.copy(isLoading = false, foodDetails = data, errorMessage = "")
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(isLoading = false, foodDetails = null, errorMessage = error.name)
                    }
                }
        }

    }

}