package com.example.store.Models

import androidx.room.Database
import com.example.store.Interface.CartDao
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cartDao(): CartDao
    companion object{
        fun getDatabase(context: Context):AppDatabase
        {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "Cart"
                ).allowMainThreadQueries().build()
        }
    }
}