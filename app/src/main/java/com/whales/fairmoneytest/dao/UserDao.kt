package com.whales.fairmoneytest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.whales.fairmoneytest.models.room.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Delete
    fun delete(user: User)
}