package com.example.falabellabeer.common

import android.app.Application
import com.example.falabellabeer.modules.AppComponent
import com.example.falabellabeer.modules.DaggerAppComponent
import com.example.falabellabeer.modules.RetrofitModule

class AppInstance  : Application() {

//    val component: AppComponent by lazy {
//        DaggerAppComponent
//            .builder()
//            .retrofitModule(RetrofitModule())
//            .build()
//    }

    override fun onCreate() {
        super.onCreate()

//        component.inject(this)
    }
}