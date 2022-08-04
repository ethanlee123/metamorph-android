package com.example.metamorph.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metamorph.ui.home.model.WebOrderParams
import com.example.metamorph.ui.home.model.WebOrderResponse
import com.example.metamorph.ui.home.network.ITransProApi
import com.example.metamorph.ui.home.repository.HomeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _jobDetails = MutableLiveData<Array<WebOrderResponse>>()
    val jobDetails: LiveData<Array<WebOrderResponse>> = _jobDetails

    var webOrderResponse: MutableLiveData<Response<List<WebOrderParams>>> = MutableLiveData()

    fun getJobDetails(webOrderParams: WebOrderParams) {
        viewModelScope.launch {
            val response = repository.getAvailableWebOrders(webOrderParams)
            webOrderResponse.value = response
        }
    }

    fun retrieveJobDetails() {
//        val httpClient = OkHttpClient()
//        httpClient.networkInterceptors().add(Interceptor { chain ->
//            val requestBuilder: Request.Builder = chain.request().newBuilder()
//            requestBuilder.header("Content-Type", "application/json")
//            chain.proceed(requestBuilder.build())
//        })
        val moshi: Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://bcause-api.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .client(httpClient)
            .build()


        val transProService: ITransProApi = retrofit.create(ITransProApi::class.java)

        val jsonObject = JSONObject()
        jsonObject.put("EmailID", "newlifechance@gmail.com")
        jsonObject.put("Password", "thisisatest")
        jsonObject.put("CultureID", "jp")
        jsonObject.put("ApplicationID", "4")

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        transProService.login(requestBody).enqueue(object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i("res", response.toString())

            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i("res", t.message ?: "null msg")
            }

        })

//        transProService.getAvailableWebOrders().enqueue(object : Callback<Any> {
//            override fun onResponse(call: Call<Any>, response: Response<Any>) {
//                Log.i("res", response.toString())
//            }
//
//            override fun onFailure(call: Call<Any>, t: Throwable) {
//                Log.i("res", t.message ?: "null msg")
//
//            }
//
//        })
    }
}