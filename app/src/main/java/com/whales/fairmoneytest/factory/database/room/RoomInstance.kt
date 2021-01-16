package com.whales.fairmoneytest.factory.database.room

import androidx.room.Room
import com.whales.fairmoneytest.MainApplication
import com.whales.fairmoneytest.database.room.AppDatabase

class RoomInstance private constructor() {

    private object SINGLE {
        val INST = Room.databaseBuilder(
            MainApplication.applicationContext(),
            AppDatabase::class.java, "database-name"
        ).build()
    }

    companion object {
        val instance: AppDatabase by lazy { SINGLE.INST}
    }
}