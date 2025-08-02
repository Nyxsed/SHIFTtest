package ru.nyxsed.shifttest.presentation.userinfo

data class UserInfoState(
    val firstName : String = "",
    val lastName : String = "",
    val pictureMediumUrl: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val country: String = "",
    val city: String = "",
    val state: String = "",
    val street: String = "",
    val house: Int = 0,
    val latitude: String = "",
    val longitude: String = "",
)
