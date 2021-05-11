//package com.whales.carbontest.dao
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.whales.carbontest.models.room.UserDetails
//
//@Dao
//interface UserDetailsDao {
//    @Query("SELECT * FROM userdetails")
//    fun getAll(): LiveData<List<UserDetails>>
//
//    @Query("SELECT * FROM userdetails WHERE uid LIKE :uid LIMIT 1")
//    fun getUserDetails(uid: Int): LiveData<UserDetails>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertUserDetails(vararg usersDetails: UserDetails)
//
//    @Delete
//    fun delete(usersDetails: UserDetails)
//}