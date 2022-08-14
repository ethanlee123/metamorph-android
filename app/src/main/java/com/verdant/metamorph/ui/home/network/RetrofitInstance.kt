package com.verdant.metamorph.ui.home.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL = "https://bcause-api.com/"
    val moshi: Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ITransProApi by lazy {
        retrofit.create(ITransProApi::class.java)
    }
}