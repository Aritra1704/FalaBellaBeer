package com.example.falabellabeer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.falabellabeer.R
import com.example.falabellabeer.modules.data.Beer
import org.jetbrains.anko.find

class BeerAdapters(val context: Context) : RecyclerView.Adapter<BeerAdapters.BeerHolder>() {
    private lateinit var beerList:List<Beer>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerAdapters.BeerHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_beer, parent, false)
        return BeerHolder(itemView)
    }

    fun refresh(users: List<Beer>) {
        this.beerList = users
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BeerAdapters.BeerHolder, position: Int) {
        holder.tvBeerStyle?.let { it.text = beerList.get(position).name }
        holder.tvAlcoholName?.let { it.text = beerList.get(position).style }
        holder.tvAlcoholContent?.let { it.text = "Alcohol Content: ${beerList.get(position).abv}" }
    }

    override fun getItemCount(): Int {
        return if(::beerList.isInitialized) beerList.size else 0
    }

    fun updatePostList(postList:List<Beer>){
        this.beerList = postList
        notifyDataSetChanged()
    }

    inner class BeerHolder(internal var myView: View) : RecyclerView.ViewHolder(myView) {

        lateinit var tvBeerStyle: TextView
        lateinit var tvAlcoholName: TextView
        lateinit var tvAlcoholContent: TextView
        lateinit var ivAddToCart: ImageView

        init {
            tvBeerStyle = myView.find(R.id.tvBeerStyle)
            tvAlcoholName = myView.find(R.id.tvAlcoholName)
            tvAlcoholContent = myView.find(R.id.tvAlcoholContent)
            ivAddToCart = myView.find(R.id.ivAddToCart)
        }
    }
}

//