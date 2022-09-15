package me.kifio.organize.presentation

import me.kifio.organize.data.Reminder
import me.kifio.organize.data.RemindersRepository

class RemindersViewModel(private val repository: RemindersRepository) : BaseViewModel() {

    val reminders: List<Reminder>
        get() = repository.reminders

    var onRemindersUpdated: ((List<Reminder>) -> Unit)? = null
        set(value) {
            field = value
            onRemindersUpdated?.invoke(reminders)
        }

    fun createReminder(title: String) = with(title.trim()) {
        if (this.isNotEmpty()) {
            repository.create(this)
            onRemindersUpdated?.invoke(reminders)
        }
    }

    fun markReminder(id: String, isCompleted: Boolean) {
        repository.markReminder(id, isCompleted = isCompleted)
        onRemindersUpdated?.invoke(reminders)
    }
}