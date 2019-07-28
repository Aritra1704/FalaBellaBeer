package com.example.falabellabeer.webservices

import com.example.falabellabeer.modules.data.Beer
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

interface APICall {
    @GET("/beercraft")
    abstract fun getBeers(): Observable<ArrayList<Beer>>
}