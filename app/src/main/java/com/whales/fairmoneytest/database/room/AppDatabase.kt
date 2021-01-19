package com.whales.fairmoneytest.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.whales.fairmoneytest.BaseApplication
import com.whales.fairmoneytest.constant.DATABASE_NAME
import com.whales.fairmoneytest.dao.UserDao
import com.whales.fairmoneytest.dao.UserDetailsDao
import com.whales.fairmoneytest.models.room.User
import com.whales.fairmoneytest.models.room.UserDetails

@Database(entities = [User::class, UserDetails::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailsDao(): UserDetailsDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase().also { instance = it }
            }
        }

        private fun buildDatabase(): AppDatabase {
            return Room.databaseBuilder(BaseApplication.getContext(), AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}