package com.afac.shoppingapp.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.afac.shoppingapp.model.Clothes
import com.afac.shoppingapp.service.ClothesDatabase
import kotlinx.coroutines.launch

class ShoppingCartDetailViewModel(application: Application):BaseViewModel(application) {
    val clothesLiveData=MutableLiveData<Clothes>()


     fun getDataFromRoom(id:Int){
        launch{
             val dao=ClothesDatabase(getApplication()).clothesDao()
             val clothes=dao.getClothes(id)
             clothesLiveData.value=clothes
         }


    }


}