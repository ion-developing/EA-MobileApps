package com.example.parcial

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EA_table")
data class tablePost (

    @PrimaryKey()
    val id:Int?,


    @ColumnInfo(name="Nombre")
    val name:String?,

    @ColumnInfo(name="Imagen")
    val img:String?

 )