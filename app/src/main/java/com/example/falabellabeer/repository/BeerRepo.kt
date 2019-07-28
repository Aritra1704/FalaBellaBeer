package com.example.falabellabeer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.falabellabeer.modules.DaggerAppComponent
import com.example.falabellabeer.modules.RetrofitModule
import com.example.falabellabeer.modules.data.Beer
import com.example.falabellabeer.webservices.APICall
import com.example.falabellabeer.webservices.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class BeerRepo {
    private var repository: BeerRepo? = null

    constructor() {
    }
}