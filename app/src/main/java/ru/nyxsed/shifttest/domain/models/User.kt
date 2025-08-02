package ru.nyxsed.shifttest.domain.models

data class User(
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
