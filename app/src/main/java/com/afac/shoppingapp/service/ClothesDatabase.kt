package com.afac.shoppingapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afac.shoppingapp.model.Clothes

@Database(entities = arrayOf(Clothes::class), version = 1)
abstract class ClothesDatabase:RoomDatabase() {
    abstract fun clothesDao():ClothesDao

    companion object{

        @Volatile private var instance:ClothesDatabase?=null

        private val lock=Any()

        operator fun invoke(context: Context)=instance?: synchronized(lock){
            instance?:makeDatabase(context).also{ instance=it}
        }

        private fun makeDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,ClothesDatabase::class.java,"clothesdatabase"
        ).build()
    }




}