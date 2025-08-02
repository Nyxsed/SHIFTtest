package ru.nyxsed.shifttest.data.models.response


import com.google.gson.annotations.SerializedName

data class GetRandomUsersResponse(
    @SerializedName("results")
    val results: List<ResultResponse>?,
    @SerializedName("Error")
    val error: String?
)