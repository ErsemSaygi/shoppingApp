package com.afac.shoppingapp.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afac.shoppingapp.model.Clothes
import com.afac.shoppingapp.service.ClothesApiService
import com.afac.shoppingapp.service.ClothesDatabase

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ShoppingCartViewModel(application: Application) : BaseViewModel(application ) {
    private val countryAPIService= ClothesApiService()
    private val disposable= CompositeDisposable()
    var clothes= MutableLiveData<List<Clothes>>()
    val clothesError= MutableLiveData<Boolean>()
    val clothesLoading= MutableLiveData<Boolean>()
    fun refreshFromAPI(){
        getDataFromAPI()
    }


    private fun getDataFromSqlite(){
        launch{
            val clths=ClothesDatabase(getApplication()).clothesDao()
            clothes.value=clths.getAllClothes()
        }


    }
    private fun storeInSQlite(list:List<Clothes>){
        launch {

            val dao=ClothesDatabase(getApplication()).clothesDao()

            dao.deleteAllClothes()
            val listlong=dao.insertAll(*list.toTypedArray())  ///listenin her elemanını gönderir
            var i=0
            while(i<list.size){

                list[i].clothesId=listlong[i].toInt()
                i++

            }

            showCountries(list)


        }

    }
    private fun showCountries(clothesList:List<Clothes>){
        clothes.value=clothesList
        clothesLoading.value=false
        clothesError.value=false
    }
    private fun getDataFromAPI(){
        clothesLoading.value=true
        disposable.add(countryAPIService.getData().
        subscribeOn(Schedulers.newThread()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribeWith(object : DisposableSingleObserver<List<Clothes>>(){
            override fun onSuccess(co: List<Clothes>) {


                storeInSQlite(co)
            }

            override fun onError(e: Throwable) {

                clothesLoading.value=false
                clothesError.value=true
                e.printStackTrace()
            }
        }))

    }
}