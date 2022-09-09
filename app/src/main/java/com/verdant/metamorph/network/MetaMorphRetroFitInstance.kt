package com.verdant.metamorph.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MetaMorphRetroFitInstance {

    init {
        System.loadLibrary("native-lib")
    }

    external fun mmApiKey(): String

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mmApiKey())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: IMetaMorphApi by lazy {
        retrofit.create(IMetaMorphApi::class.java)
    }
}