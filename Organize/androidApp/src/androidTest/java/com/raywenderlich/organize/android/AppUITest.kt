package com.raywenderlich.organize.android;

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import me.kifio.organize.android.root.MainActivity
import org.junit.Rule;
import org.junit.Test

class AppUITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAboutButtonExistence() {
        composeTestRule
            .onNodeWithContentDescription("aboutButton")
            .assertIsDisplayed()
    }

    @Test
    fun testOpeningAndClosingAboutPage() {
        composeTestRule
            .onNodeWithContentDescription("aboutButton")
            .performClick()

        composeTestRule
            .onNodeWithText("About Device")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("Up Button")
            .performClick()

        composeTestRule
            .onNodeWithText("Reminders")
            .assertIsDisplayed()
    }
}