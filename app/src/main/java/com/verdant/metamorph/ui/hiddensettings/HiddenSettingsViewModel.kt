package com.verdant.metamorph.ui.hiddensettings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class HiddenSettingsViewModel : ViewModel() {

    private val _deviceTokenText = MutableLiveData<String>()
    val deviceTokenText: LiveData<String> = _deviceTokenText

    init {
        getDeviceToken()
    }

    /**
     * Device token can be used to test push notifs from triggered from Firebase.
     */
    private fun getDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i(HiddenSettingsActivity.TAG, "Could not retrieve device token")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            _deviceTokenText.value = token
        })
    }
}