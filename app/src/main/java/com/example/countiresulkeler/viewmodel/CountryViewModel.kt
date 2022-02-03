package com.example.countiresulkeler.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countiresulkeler.model.Country
import com.example.countiresulkeler.service.CountryDataBase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application){
    val country= MutableLiveData<Country>()
    fun getDataFromRoom(Uuid:Int){
        //val mycountry=Country("Ankara","TL","Url","Türkçe","Türkiye","Asia")
       // country.value=mycountry
        launch {
            val dao=CountryDataBase(context = getApplication()).countryDAO()
            val mYCountry=dao.getCounty(Uuid)
            country.value=mYCountry
        }
    }
}