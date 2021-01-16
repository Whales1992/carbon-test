package com.whales.fairmoneytest.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "picture") val picture: String?
)