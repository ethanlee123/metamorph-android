package com.verdant.metamorph.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fetching device token"
    }
    val text: LiveData<String> = _text

    /**
     * Device token can be used to test push notifs from triggered from Firebase.
     */
    fun getDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ProfileFragment.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            _text.value = token

            Log.d(ProfileFragment.TAG, "Device Token: $token")
        })
    }
}