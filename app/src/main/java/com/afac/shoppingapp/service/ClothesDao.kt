package com.afac.shoppingapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.afac.shoppingapp.model.Clothes
@Dao
interface ClothesDao {
    @Insert
    suspend fun insertAll(vararg clothes: Clothes):List<Long>

    @Query("SELECT * FROM Clothes WHERE clothesId=:clothesId")
    suspend fun getClothes(clothesId:Int):Clothes

    @Query("SELECT * FROM Clothes")
    suspend fun getAllClothes():List<Clothes>

    @Query("DELETE FROM Clothes")
    suspend fun deleteAllClothes()
}