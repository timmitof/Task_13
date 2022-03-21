package com.timmitof.retrofit.services

import com.timmitof.retrofit.dictionaries.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    private var retrofit: Retrofit? = null
    fun getClient(): Retrofit {
        if (retrofit == null){
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = getClientBuilderWithAuth().addInterceptor(logging).build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        }
        return retrofit!!
    }

    private var logHandler: ((String) -> (Unit))? = null
    fun getClientBuilderWithAuth(): OkHttpClient.Builder{
        val builder = OkHttpClient.Builder()
        val logHandler = logHandler
        if (logHandler != null)
        builder.addInterceptor {
            val request = it.request()
            logHandler("${request.method} ${request.url}")
            it.proceed(request)
        }
        return builder
    }
}