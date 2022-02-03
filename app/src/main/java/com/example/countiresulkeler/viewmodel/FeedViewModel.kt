package com.example.countiresulkeler.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countiresulkeler.model.Country
import com.example.countiresulkeler.service.CountryApıServices
import com.example.countiresulkeler.service.CountryDataBase
import com.example.countiresulkeler.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application:Application):BaseViewModel(application) {
    private val countryApıServices=CountryApıServices()
    private val disposse=CompositeDisposable()
    //kullan at objesi  birden fazla call yaparken kullanıyoruz


    private val costumPreferences=CustomSharedPreferences(getApplication());
    private var refreshTime=10*60*1000*1000*1000L//nanosaniyeyei aldık //10 dakika sonrası için



    val countries= MutableLiveData<List<Country>>()
    val countryError= MutableLiveData<Boolean>()
    val countryLooding= MutableLiveData<Boolean>()

    fun refreshData(){
        //10 dakikadan sonra artık apiden alıcak onun çncesinde  sqlitten alıcak
        val updateTime=costumPreferences.getTime()
        if(updateTime!=null && updateTime!=0L  && System.nanoTime()-updateTime<refreshTime){
            //10 dakikayi geçmedi
            getDataFromSQLite();
        }else{
            getDataFromAPI();
        }
    }
    fun refreshFromAPi(){
        getDataFromAPI();
    }
    private fun getDataFromSQLite(){
        countryLooding.value=true;
        launch {
            val countries=CountryDataBase(getApplication()).countryDAO().getAllCountries()
            showCountryies(countries)
            Toast.makeText(getApplication(), "SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataFromAPI(){
        countryLooding.value=true;
        disposse.add(
            countryApıServices.getData()
                .subscribeOn(Schedulers.newThread())//new thread vreya io // arka planda çalışması için//io dediği arka plan
                .observeOn(AndroidSchedulers.mainThread())//main Threadde çalışıcak
                .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(), "Api", Toast.LENGTH_SHORT).show()
                    }
                    override fun onError(e: Throwable) {
                        countryLooding.value=false
                        countryError.value=true
                        e.printStackTrace()
                    }

                })
        )
    }
    private fun showCountryies(t: List<Country>){
        countries.value=t
        countryLooding.value=false
        countryError.value=false
    }
    private fun storeInSQLite(list: List<Country>){
        launch {
            val dao=CountryDataBase(getApplication()).countryDAO()
            dao.deleteAll()
            val storageListLong=dao.insertAll(*list.toTypedArray())//toTypedArray dizideki elemanları tek tek döntürür 1,2,3,4...n şeklinde
            var  i =0;
            while (i<storageListLong.size){
                list[i].UUID=storageListLong[i].toInt()
                i++;
            }
            showCountryies(list)
        }
        costumPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposse.clear()
    }
}
