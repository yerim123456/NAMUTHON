package com.example.namuthon.data

import android.util.Log
import com.example.namuthon.BuildConfig.BASE_URL
import com.example.namuthon.data.API.API_TAG
import com.example.namuthon.data.api.ExampleApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit

object ApiPool {
    val getExample = RetrofitPool.retrofit.create(ExampleApiService::class.java)
}


object RetrofitPool {
    val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() -> Log.d(API_TAG, JSONObject(message).toString(4))

                message.isJsonArray() -> Log.d(API_TAG, JSONArray(message).toString(4))

                else -> {
                    Log.d(API_TAG, "CONNECTION INFO -> $message")
                }
            }
        }

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient).build()
    }
}

object API {
    const val API_TAG = "Retrofit2"
}