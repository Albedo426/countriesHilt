package com.example.countiresulkeler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countiresulkeler.R
import com.example.countiresulkeler.databinding.ItemCountryBinding
import com.example.countiresulkeler.model.Country
import com.example.countiresulkeler.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CounrtyAdapter(val countryList:ArrayList<Country>): RecyclerView.Adapter<CounrtyAdapter.CounrtyViewHolder>(),CountryClickListener {


    class CounrtyViewHolder(var view: ItemCountryBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CounrtyViewHolder, position: Int) {
        //context herhangi bir görsel nerneden çekilir
        //view gelmiyorsa  CounrtyViewHolder verisindeki viewin soluna var ekle
        holder.view.myModel=countryList[position]
        holder.view.listener=this
        /*
        holder.itemView.name.text=countryList[position].countryName
        holder.itemView.region.text=countryList[position].countryRegion

        holder.itemView.imageView.downloadFromUrl(countryList[position].countryImgUrl,
            placeHolderProgressBar(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].UUID)
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounrtyViewHolder {
        //val view=LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
        val infilater=LayoutInflater.from(parent.context)
        val view =DataBindingUtil.inflate<ItemCountryBinding>(infilater,R.layout.item_country,parent,false)
        return CounrtyViewHolder(view)
    }

    fun updateCounryList(newcountryList:List<Country>){
        countryList.clear();
        countryList.addAll(newcountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClick(v: View) {
        val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment( v.myCardId.text.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}