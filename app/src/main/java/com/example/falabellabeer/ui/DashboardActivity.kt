package com.example.falabellabeer.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity;
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.falabellabeer.R
import com.example.falabellabeer.ui.adapters.BeerAdapters
import com.example.falabellabeer.viewmodel.BeerVM

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class DashboardActivity : AppCompatActivity() {

    private var beerVM: BeerVM? = null
    lateinit var ivCart: ImageView
    lateinit var ivSort: ImageView
    lateinit var ivFilter: ImageView
    lateinit var edtSearch: EditText
    lateinit var rvBeer: RecyclerView
    lateinit var tvNoData: TextView
    lateinit var pbLoading: ProgressBar
    lateinit var adapter: BeerAdapters

    var sort_ascending: Boolean = true
    var seletedFilter: String = "LAGER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initialiseControls()

        beerVM = ViewModelProviders.of(this).get(BeerVM::class.java)
        beerVM!!.init()
        beerVM!!.getBeerMutable()?.let {
            it.observe(this, Observer {list ->
                     if(list != null) {
                         adapter.refresh(list)
                         tvNoData.visibility = View.GONE
                     } else {
                         tvNoData.visibility = View.VISIBLE
                         rvBeer.visibility = View.GONE
                     }
                pbLoading.visibility = View.GONE
            })
        }

        setAdapter()

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {

                adapter.refresh(beerVM!!.search(editable.toString()))
            }
        })

    }

    override fun onResume() {
        super.onResume()

        edtSearch.clearFocus()
        hideKeyBoard()
    }
    fun onSort(view: View) {
        adapter.refresh(beerVM!!.sort(sort_ascending))
        sort_ascending = !sort_ascending
    }

    fun onFilter(view: View) {
        seletedFilter = beerVM!!.nextFilter(seletedFilter)
        adapter.refresh(beerVM!!.filter(seletedFilter))
        toast("Filtered by ${seletedFilter}")
    }

    fun setAdapter() {
        adapter = BeerAdapters(this)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        rvBeer.setLayoutManager(mLayoutManager)
        rvBeer.setItemAnimator(DefaultItemAnimator())
        rvBeer.setAdapter(adapter)
    }

    fun initialiseControls() {
        ivCart = find(R.id.ivCart)
        ivSort = find(R.id.ivSort)
        ivFilter = find(R.id.ivFilter)
        edtSearch = find(R.id.edtSearch)
        rvBeer = find(R.id.rvBeer)
        tvNoData = find(R.id.tvNoData)
        pbLoading = find(R.id.pbLoading)
    }

    fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
