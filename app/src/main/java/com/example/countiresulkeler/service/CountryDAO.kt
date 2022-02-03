package com.example.countiresulkeler.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.countiresulkeler.model.Country

@Dao
interface CountryDAO {

    //data accses object
    @Insert
    suspend fun insertAll(vararg contries:Country):List<Long>
    //ınsert ınsert into
    //supend couroutine //fonksiyonları durduğpur çalıştırma isteğe bağlıistediğin zamans
    //vararg sayısını bilmediğin zaman birden fazla veri eklenebilir demek var 1 tane vararg n tane
    //List<Long> ise primary keyi döndürüyor

    @Query("Select * from Country")
    suspend fun getAllCountries():List<Country>

    @Query("Select * from Country where UUID=:counrtyId")
    suspend fun getCounty(counrtyId:Int):Country


    @Query("delete  from Country ")
    suspend fun deleteAll()
}