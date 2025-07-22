package com.coding.meal.food.presentation.food_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.meal.core.domain.onError
import com.coding.meal.core.domain.onSuccess
import com.coding.meal.core.util.FoodType
import com.coding.meal.food.domain.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FoodListViewModel(
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FoodListState())
    val state = _state.asStateFlow()


    fun onAction(action: FoodListAction) {
        when (action) {
            is FoodListAction.OnFoodClick -> {
                // Handle food click in screen
            }

            is FoodListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
                getFood()
            }
        }
    }

    private fun getFood() {
        viewModelScope.launch {
            val type = FoodType.currentFoodType(_state.value.selectedTabIndex)
            _state.update {
                it.copy(isLoading = true)
            }
            foodRepository.getFood(type)
                .onSuccess { data ->
                    _state.update {
                        when (type) {
                            FoodType.SEAFOOD -> it.copy(seafoodList = data)
                            FoodType.BEEF -> it.copy(beefList = data)
                        }.copy(isLoading = false, errorMessage = "")
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.name,
                            seafoodList = emptyList(),
                            beefList = emptyList()
                        )
                    }

                }
        }
    }
}


