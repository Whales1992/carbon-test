package com.whales.fairmoneytest.dao

import androidx.room.*
import com.whales.fairmoneytest.models.room.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE firstName LIKE :first AND " +
           "lastName LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(users: List<User>)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM User")
    fun deleteAllUsers()

    @Transaction
    fun updateUsers(users: List<User>) {
        deleteAllUsers()
        insertAll(users)
    }
}