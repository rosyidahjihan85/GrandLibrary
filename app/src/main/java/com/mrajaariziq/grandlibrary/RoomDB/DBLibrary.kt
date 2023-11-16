package com.mrajaariziq.grandlibrary.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [DataBuku::class, DataPinjam::class], version = 3)
abstract class DBLibrary :RoomDatabase() {
    abstract fun librarydao():LibraryDAO

    companion object{
        @Volatile
        private var instance :DBLibrary? = null
        private var key = Any()
        @Synchronized
        fun getInstance (context: Context):DBLibrary{
            if (instance == null){
                instance = Room.databaseBuilder(context,DBLibrary::class.java,"Grand Library")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}