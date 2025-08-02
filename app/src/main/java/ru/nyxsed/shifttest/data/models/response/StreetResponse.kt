package ru.nyxsed.shifttest.data.models.response


import com.google.gson.annotations.SerializedName

data class StreetResponse(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("number")
    val number: Int = 0
)