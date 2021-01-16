package com.whales.fairmoneytest.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whales.fairmoneytest.dao.UserDao
import com.whales.fairmoneytest.models.room.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}