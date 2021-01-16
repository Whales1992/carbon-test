package com.whales.fairmoneytest.factory.database.room

import androidx.room.Transaction
import com.whales.fairmoneytest.models.room.User

class DaoInstance {
    private val userDao = RoomInstance.instance.userDao()

    fun getAllUsers(): List<User>{
        return userDao.getAll()
    }

    fun deleteUser(user: User) {
        return userDao.delete(user)
    }

    fun updateUsers(users: List<User>) {
        return userDao.updateUsers(users)
    }

}