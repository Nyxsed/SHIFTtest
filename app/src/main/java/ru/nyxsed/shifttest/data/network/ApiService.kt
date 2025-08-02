package ru.nyxsed.shifttest.data.network


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nyxsed.shifttest.data.models.response.GetRandomUsersResponse

interface ApiService {
    @GET("api")
    suspend fun getRandomUsers(
        @Query("results") results: Int
    ): Response<GetRandomUsersResponse>
}