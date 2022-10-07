package com.example.parcial

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import  androidx.room.OnConflictStrategy

@Dao
interface PostDao {

    @Query("SELECT * FROM EA_table")
    fun getAll():List<tablePost>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tablePost: tablePost)

}