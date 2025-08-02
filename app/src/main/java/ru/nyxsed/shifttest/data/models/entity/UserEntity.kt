package ru.nyxsed.shifttest.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val pictureThumbUrl: String,
    val pictureMediumUrl: String,
    val country: String,
    val city: String,
    val state: String,
    val street: String,
    val house: Int,
    val latitude: String,
    val longitude: String,
    val phoneNumber: String,
    val email: String,
)
