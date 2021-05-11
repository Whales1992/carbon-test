//package com.whales.carbontest.repository
//
//import androidx.lifecycle.LiveData
//import com.whales.carbontest.database.room.AppDatabase
//import com.whales.carbontest.models.room.User
//import com.whales.carbontest.models.room.UserDetails
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.async
//
//class DataBase() {
//
//    fun addUser(user: User) {
//        GlobalScope.async {
//            AppDatabase.getInstance().userDao().insert(user)
//        }
//    }
//
//    fun getAllUser(): LiveData<List<User>> {
//        return AppDatabase.getInstance().userDao().getAll()
//    }
//
//    fun addUserDetails(userDetails: UserDetails) {
//        AppDatabase.getInstance().userDetailsDao().insertUserDetails(userDetails)
//    }
//
//    fun getUserDetails(): LiveData<List<UserDetails>> {
//        return AppDatabase.getInstance().userDetailsDao().getAll()
//    }
//
//}