package com.coding.meal.food.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.coding.meal.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FoodListAndDetailsTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun foodList_navigatesToDetailsScreen() {
        // 1) Wait for the list screen
        rule.onNodeWithTag("FoodListScreen")
            .assertIsDisplayed()

        rule.waitUntil(timeoutMillis = 5_000) {
            rule.onAllNodesWithTag("FoodListItem")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        // 3) Click the first item
        rule.onAllNodesWithTag("FoodListItem")[0]
            .performClick()

        // 4) Verify details screen is shown
        rule.onNodeWithTag("FoodDetailsScreen")
            .assertIsDisplayed()
        rule.onNodeWithTag("FoodDetailsTitle")
            .assertIsDisplayed()


    }
}
