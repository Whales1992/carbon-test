package com.whales.fairmoneytest.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetails(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "dateOfBirth") val dateOfBirth: String?,
    @ColumnInfo(name = "registerDate") val registerDate: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "picture") val picture: String?,
    @ColumnInfo(name = "location") val location: String?
)