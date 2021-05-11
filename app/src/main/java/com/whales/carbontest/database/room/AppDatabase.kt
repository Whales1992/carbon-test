//package com.whales.carbontest.database.room
//
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.whales.carbontest.BaseApplication
//import com.whales.carbontest.constant.DATABASE_NAME
//import com.whales.carbontest.dao.UserDao
//import com.whales.carbontest.dao.UserDetailsDao
//import com.whales.carbontest.models.room.User
//import com.whales.carbontest.models.room.UserDetails
//
//@Database(entities = [User::class, UserDetails::class], version = 1, exportSchema = true)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//    abstract fun userDetailsDao(): UserDetailsDao
//
//    companion object {
//
//        // For Singleton instantiation
//        @Volatile private var instance: AppDatabase? = null
//
//        fun getInstance(): AppDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase().also { instance = it }
//            }
//        }
//
//        private fun buildDatabase(): AppDatabase {
//            return Room.databaseBuilder(BaseApplication().applicationContext, AppDatabase::class.java, DATABASE_NAME)
//                .build()
//        }
//    }
//}