package com.example.countiresulkeler.ui.country_single_page_fragmet

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.countiresulkeler.app.base.BaseViewModel
import com.example.countiresulkeler.app.model.Country
import com.example.countiresulkeler.app.service.CountryDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


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