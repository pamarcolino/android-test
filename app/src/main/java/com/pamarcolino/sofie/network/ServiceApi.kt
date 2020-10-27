package com.pamarcolino.sofie.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceApi {

    companion object {

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private val BASE_URL = "https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/"

        private fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        fun dataService() = provideRetrofit().create(ServiceData::class.java)
    }
}