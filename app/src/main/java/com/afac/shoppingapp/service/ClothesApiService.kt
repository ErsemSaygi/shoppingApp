package com.afac.shoppingapp.service;

import com.afac.shoppingapp.model.Clothes




import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class ClothesApiService {

    private val BASE_URL = "https://raw.githubusercontent.com/";
    private val api=  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ClothesApi::class.java)



    fun getData():Single<List<Clothes>>

    {
        return api.getClothes()
    }


}
