package com.example.falabellabeer.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.falabellabeer.R
import com.example.falabellabeer.modules.data.Beer
import com.example.falabellabeer.repository.BeerRepo
import com.example.falabellabeer.utils.FilterCategory
import com.example.falabellabeer.webservices.APICall
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BeerVM : BaseVM() {

    @Inject
    lateinit var apiCall: APICall

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadBeers() }

    private var mRepository: BeerRepo? = BeerRepo()
    private var beerLiveData: MutableLiveData<List<Beer>>? = null

    fun init() {
        if (beerLiveData != null)
            return

        loadBeers()
    }

    fun getBeerMutable(): MutableLiveData<List<Beer>>? {
        beerLiveData = MutableLiveData()
        return beerLiveData
    }

    fun getBeers() : List<Beer> {
        beerLiveData?.let {
            return beerLiveData!!.value!!
        } ?: run {
            return beerLiveData!!.value!!
        }
    }

    fun loadBeers() {
        subscription = apiCall.getBeers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveBeerListStart() }
            .doOnTerminate { onRetrieveBeerListFinish() }
            .subscribe(
                { result -> onRetrieveBeerListSuccess(result) },
                { onRetrieveBeerListError() }
            )
    }

    private fun onRetrieveBeerListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveBeerListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveBeerListSuccess(beerList:List<Beer>){
        beerLiveData!!.value = beerList
    }

    private fun onRetrieveBeerListError(){
        errorMessage.value = R.string.error_fetching

    }

    fun search(search: String): List<Beer> {
        var searchedList : List<Beer>?
        searchedList = beerLiveData!!.value?.let {
            it.filter { it -> it.name.contains(search) }
        }
        return searchedList!!;
    }

    fun sort(ascend: Boolean): List<Beer> {
        var searchedList : List<Beer>?
        searchedList = beerLiveData!!.value?.let {
            if(ascend) it.sortedBy { it -> it.abv }
            else it.sortedByDescending { it -> it.abv }
        }
        return searchedList!!;
    }

    fun filter(nextFilter: String): List<Beer> {
        var searchedList : List<Beer>?

        searchedList = beerLiveData!!.value?.let {
            it.filter { it -> it.style.toLowerCase().contains(nextFilter.toLowerCase()) }
        }
        return searchedList!!;
    }

    fun nextFilter(selectedFilter: String): String {
        var nextFilter: String = when(FilterCategory.create(selectedFilter)) {
            FilterCategory.ALE -> FilterCategory.LAGER.filterType
            FilterCategory.LAGER -> FilterCategory.IPA.filterType
            FilterCategory.IPA -> FilterCategory.ALE.filterType
        }

        return nextFilter
    }
}
