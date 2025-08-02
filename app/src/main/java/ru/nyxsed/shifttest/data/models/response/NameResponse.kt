package ru.nyxsed.shifttest.data.models.response


import com.google.gson.annotations.SerializedName

data class NameResponse(
    @SerializedName("first")
    val first: String = "",
    @SerializedName("last")
    val last: String = ""
)