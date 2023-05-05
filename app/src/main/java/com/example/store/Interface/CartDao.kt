package com.example.store.Interface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.store.Models.CartData

@Dao
interface CartDao {
    @Query("SELECT * FROM CartData")
    fun getAll(): List<CartData>

    @Insert
    fun insertAll(vararg todolist: CartData)

    @Update
    fun update(todo: CartData)

    @Delete
    fun delete(todo: CartData)

    @Query("DELETE FROM CartData")
    fun clearTable()
}