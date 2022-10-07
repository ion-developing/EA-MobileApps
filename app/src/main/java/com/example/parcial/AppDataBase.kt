package com.example.parcial

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [tablePost::class], version = 1)
abstract  class AppDataBase:RoomDatabase() {

    abstract fun postDao():PostDao

    companion object{
        @Volatile
        private  var INSTANCE:AppDataBase?=null

        fun getDatabase(context: Context):AppDataBase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_databases2"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }

}