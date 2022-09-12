package me.kifio.organize.data

import me.kifio.organize.domain.UUID

data class Reminder(val id: String, val title: String, val isCompleted: Boolean = false)

class RemindersRepository {
    private val _reminders = mutableListOf<Reminder>()
    val reminders: List<Reminder>
        get() { return _reminders }

    fun create(title: String) {
        _reminders.add(
            Reminder(UUID().toString(), title, false)
        )
    }

    fun markReminder(id: String, isCompleted: Boolean) {
        val index = _reminders.indexOfFirst { id == it.id }

        if (index > -1) {
            _reminders[index] = _reminders[index].copy(isCompleted = isCompleted)
        }
    }
}