package com.afac.shoppingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Clothes(
    @ColumnInfo(name="productName")
    @SerializedName("productName")
    val clothesName:String?,

    @ColumnInfo(name="price")
    @SerializedName("price")
    val clothesPrice:Double?,

    @ColumnInfo(name="firmName")
    @SerializedName("firmName")
    val clothesCompany:String?,

    @ColumnInfo(name="imagePath")
    @SerializedName("imagePath")
    val clothesImage:String?){
        @PrimaryKey(autoGenerate = true)
        var clothesId:Int=0
    }






