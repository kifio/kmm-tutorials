package me.kifio.organize.presentation

import me.kifio.organize.data.Reminder
import me.kifio.organize.data.RemindersRepository

class RemindersViewModel : BaseViewModel() {

    private val repositrory = RemindersRepository()

    private val reminders: List<Reminder>
        get() = repositrory.reminders

    var onRemindersUpdated: ((List<Reminder>) -> Unit)? = null
        set(value) {
            field = value
            onRemindersUpdated?.invoke(reminders)
        }

    fun createReminder(title: String) = with (title.trim()) {
        if (this.isNotEmpty()) {
            repositrory.create(this)
            onRemindersUpdated?.invoke(reminders)
        }
    }

    fun markReminder(id: String, isCompleted: Boolean) {
        repositrory.markReminder(id, isCompleted = isCompleted)
        onRemindersUpdated?.invoke(reminders)
    }
}