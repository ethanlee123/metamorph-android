package com.verdant.metamorph.ui.notifications.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.verdant.metamorph.ui.notifications.NotificationsViewModel
import com.verdant.metamorph.ui.notifications.repository.NotificationsRepository

class NotificationsViewModelFactory(private val repository: NotificationsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationsViewModel(repository) as T
    }
}