package com.verdant.metamorph.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verdant.metamorph.model.NotificationRowResponse
import com.verdant.metamorph.ui.notifications.repository.NotificationsRepository
import kotlinx.coroutines.launch

class NotificationsViewModel(private val repository: NotificationsRepository) : ViewModel() {

    private val _notificationRows = MutableLiveData<List<NotificationRowResponse>>()
    val notificationRows: LiveData<List<NotificationRowResponse>> = _notificationRows

    fun fetchPushNotifications() {
        viewModelScope.launch {
            val response = repository.fetchPushNotifications()
            _notificationRows.value = response.body()
        }
    }
}