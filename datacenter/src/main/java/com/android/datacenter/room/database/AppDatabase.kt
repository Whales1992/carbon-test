//package com.android.datacenter.room.database
//
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.android.datacenter.DATABASE_NAME
//import com.android.datacenter.models.Movie
//import com.android.datacenter.models.MovieDetail
//import com.android.datacenter.room.dao.MovieDao
//import com.android.datacenter.room.dao.MovieDetailDao
//import com.whales.carbontest.BaseApplication
//
//@Database(entities = [Movie::class, MovieDetail::class], version = 1, exportSchema = true)
//abstract class AppDatabase : RoomDatabase() {
////    abstract fun movieDao(): MovieDao
////    abstract fun movieDetailDao(): MovieDetailDao
//
//    companion object {
//
//        // For Singleton instantiation
////        @Volatile private var instance: AppDatabase? = null
////
////        fun getInstance(): AppDatabase {
////            return instance ?: synchronized(this) {
////                instance ?: buildDatabase().also { instance = it }
////            }
////        }
////
////        private fun buildDatabase(): AppDatabase {
////            return Room.databaseBuilder(BaseApplication().applicationContext, AppDatabase::class.java, DATABASE_NAME)
////                .build()
////        }
//    }
//}