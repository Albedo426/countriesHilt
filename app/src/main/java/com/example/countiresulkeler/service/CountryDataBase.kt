package com.example.countiresulkeler.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countiresulkeler.model.Country

// veri tabanına ekleme işlemi eğer veri tabanına birden fazla talbo ekleyecek isen array of yerine , ile farklı entitityi eklemelisin
@Database(entities = arrayOf(Country::class),version = 1)
abstract class CountryDataBase :RoomDatabase() {

    abstract fun countryDAO():CountryDAO

    //singleton veir olutşuysa oluna veri veri oluşmadıysa yeni oluşturan yaklaşım
    companion object{
        @Volatile private var instance:CountryDataBase?=null
        //Volatile herhenagi bir değişkeni herhangi bir threade anında görünür olur farklı thredlerde çağırılsın diye
        private val lock =Any()
        operator fun invoke(context:Context) = instance ?: synchronized(lock){
            instance?:makeDataBase(context).also {
                instance=it
            }
            //also bunu yaqp sonra da bunu yap demek eğer olmadıysa hiç yapmaz  olursa yapar gibi çalışır
        }
        //synchronized threadlardan sadece biri erişir  işlemi bitinde diğerine geçicek

        private fun makeDataBase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            CountryDataBase::class.java,
            "counrtyDatabase").build()
        //databaseyi oluşturan şey bu
    }
}