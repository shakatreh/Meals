package com.coding.meal.food.presentation.food_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.meal.core.domain.onError
import com.coding.meal.core.domain.onSuccess
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

    fun getFood() {
        viewModelScope.launch {

            val type = currentFoodType()

            if (isCached(type)) return@launch

            _state.update {
                it.copy(isLoading = true)
            }
            foodRepository.getFood(type.value)
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

    private fun currentFoodType(): FoodType =
        if (_state.value.selectedTabIndex == 0) FoodType.SEAFOOD else FoodType.BEEF

    private fun isCached(type: FoodType): Boolean {
        return when (type) {
            FoodType.SEAFOOD -> _state.value.seafoodList.isNotEmpty()
            FoodType.BEEF -> _state.value.beefList.isNotEmpty()
        }
    }
}


enum class FoodType(val value: String) {
    SEAFOOD("Seafood"),
    BEEF("Beef")
}