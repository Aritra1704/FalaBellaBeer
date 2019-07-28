package com.example.falabellabeer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.falabellabeer.modules.AppComponent
import com.example.falabellabeer.modules.DaggerAppComponent
import com.example.falabellabeer.modules.RetrofitModule

abstract class BaseVM : ViewModel() {
    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .networkModule(RetrofitModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is BeerVM -> injector.inject(this)
        }
    }
}