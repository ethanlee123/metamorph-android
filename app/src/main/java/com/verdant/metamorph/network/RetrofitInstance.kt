package com.verdant.metamorph.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Refactor this to query metamorph's api
 */
@Deprecated(message = "Migrate to metamorph's api")
object RetrofitInstance {
    private const val BASE_URL = "https://bcause-api.com/"
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