package ru.nyxsed.shifttest.data.models.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("uuid")
    val uuid: String = "",
)