package com.verdant.metamorph.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MetaMorphRetroFitInstance {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: IMetaMorphApi by lazy {
        retrofit.create(IMetaMorphApi::class.java)
    }
}