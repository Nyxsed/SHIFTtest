package ru.nyxsed.shifttest.data.models.response


import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("email")
    val email: String = "",
    @SerializedName("location")
    val location: LocationResponse = LocationResponse(),
    @SerializedName("name")
    val name: NameResponse = NameResponse(),
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("picture")
    val picture: PictureResponse = PictureResponse(),
    @SerializedName("login")
    val login: LoginResponse = LoginResponse()

)