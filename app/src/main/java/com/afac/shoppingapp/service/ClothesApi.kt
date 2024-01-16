package com.afac.shoppingapp.service;

import com.afac.shoppingapp.model.Clothes




import io.reactivex.Single;
import retrofit2.http.GET;

interface ClothesApi {


    @GET("ErsemSaygi/clothesJson/main/raw.json")
    fun getClothes():Single<List<Clothes>>
}
