package ru.nyxsed.shifttest.data.models.response


import com.google.gson.annotations.SerializedName

data class CoordinatesResponse(
    @SerializedName("latitude")
    val latitude: String = "",
    @SerializedName("longitude")
    val longitude: String = ""
)