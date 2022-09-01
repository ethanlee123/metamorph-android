package com.verdant.metamorph.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verdant.metamorph.ui.home.repository.HomeRepository
import com.verdant.metamorph.ui.notifications.repository.NotificationsRepository

class NotificationsViewModel(private val repository: NotificationsRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    private val _notificationRows = MutableLiveData<List<String>>()
    val notificationRows: LiveData<List<String>> = _notificationRows

    fun fetchPushNotifications() {
        repository.fetchPushNotifications()
    }

}