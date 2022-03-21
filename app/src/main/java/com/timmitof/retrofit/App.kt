package com.timmitof.retrofit

import android.app.Application
import com.timmitof.retrofit.services.Api
import com.timmitof.retrofit.services.RetrofitClient

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        services = RetrofitClient.getClient().create(Api::class.java)

    }

    companion object{
        var services: Api? = null
    }
}