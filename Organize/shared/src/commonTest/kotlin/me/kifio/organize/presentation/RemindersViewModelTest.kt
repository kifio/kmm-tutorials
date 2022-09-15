package me.kifio.organize.presentation

import me.kifio.organize.Modules.initKoin
import me.kifio.organize.Platform
import me.kifio.organize.data.RemindersRepository
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class RemindersViewModelTest : KoinTest {
    private val viewModel: RemindersViewModel by inject()

    @BeforeTest
    fun setUp() {
        initKoin()
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testCreatingReminder() {
        val title = "New Title"
        viewModel.createReminder(title)

        val count = viewModel.reminders.count {
            it.title == title
        }

        assertTrue(
            actual = count == 1,
            message = "Reminder with title: $title wasn't created.",
        )
    }
}