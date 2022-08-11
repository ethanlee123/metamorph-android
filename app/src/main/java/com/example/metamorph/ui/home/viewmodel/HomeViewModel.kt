package com.example.metamorph.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metamorph.model.OrderDetailsByIdResponse
import com.example.metamorph.model.WebOrderParams
import com.example.metamorph.model.WebOrderResponse
import com.example.metamorph.ui.home.network.ITransProApi
import com.example.metamorph.ui.home.repository.HomeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    var webOrderResponse: MutableLiveData<List<WebOrderResponse>> = MutableLiveData()

    fun getWebOrders(webOrderParams: WebOrderParams) {
        viewModelScope.launch {
            val response = repository.getAvailableWebOrders(webOrderParams)
            webOrderResponse.value = response.body()
        }
    }
}