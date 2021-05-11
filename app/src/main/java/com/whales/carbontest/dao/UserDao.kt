//package com.whales.carbontest.dao
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.whales.carbontest.models.room.User
//
//@Dao
//interface UserDao {
//    @Query("SELECT * FROM user")
//    fun getAll(): LiveData<List<User>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(vararg user: User)
//
//    @Delete
//    fun delete(user: User)
//}