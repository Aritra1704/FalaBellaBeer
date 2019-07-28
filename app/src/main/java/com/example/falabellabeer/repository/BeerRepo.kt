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

//    private var apiCalls: APICall
//    @Inject
//    internal var retrofitService: RetrofitService? = null

    constructor() {
//        val appComponent = DaggerAppComponent.builder().retrofitModule(RetrofitModule()).build()
//        appComponent.inject(this)
//        apiCalls = retrofitService!!.createService(APICall::class.java)
    }

    fun getMarkets(): MutableLiveData<ArrayList<Beer>> {
        val variantData = MutableLiveData<ArrayList<Beer>>()
//        apiCalls.getBeerMutable().enqueue(object : Callback<ArrayList<Beer>> {
//            override fun onResponse(call: Call<ArrayList<Beer>>, response: Response<ArrayList<Beer>>) {
//                Log.d("onResponse", response.toString())
//
//                try {
//                    if (response.isSuccessful) {
//                        variantData.setValue(response.body())
//                    }
//
//                } catch (ex: Exception) {
//                    ex.printStackTrace()
//                }
//
//            }
//
//            override fun onFailure(call: Call<ArrayList<Beer>>, t: Throwable) {
//                Log.d("onFailure", t.toString())
//                variantData.setValue(null)
//            }
//        })

        return variantData
    }
}