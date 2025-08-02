package ru.nyxsed.shifttest.data.models.response


import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("city")
    val city: String = "",
    @SerializedName("coordinates")
    val coordinates: CoordinatesResponse = CoordinatesResponse(),
    @SerializedName("country")
    val country: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("street")
    val street: StreetResponse = StreetResponse()
)