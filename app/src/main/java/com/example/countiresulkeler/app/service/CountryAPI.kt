package com.example.countiresulkeler.app.service

import com.example.countiresulkeler.app.model.Country
import io.reactivex.Single
import retrofit2.http.GET


interface CountryAPI {
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Country>>//liste içinde countryler fgeliyor
    //veriyi füncelliyip devamlı almak için observable
    //bir defa almak için  single

}