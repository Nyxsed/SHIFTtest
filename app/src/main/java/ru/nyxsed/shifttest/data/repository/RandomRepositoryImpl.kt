package ru.nyxsed.shifttest.data.repository

import ru.nyxsed.shifttest.data.mapper.RandomUserMapper
import ru.nyxsed.shifttest.data.network.ApiService
import ru.nyxsed.shifttest.data.network.GetRandomUsersResult
import ru.nyxsed.shifttest.domain.repository.RandomRepository
import java.io.IOException

class RandomRepositoryImpl(
    private val apiService: ApiService,
    private val mapper: RandomUserMapper,
) : RandomRepository {
    override suspend fun getRandomUsers(userAmount: Int): GetRandomUsersResult<Any> {
        return try {
            val response = apiService.getRandomUsers(userAmount)
            if (response.isSuccessful) {
                val responseBody = response.body()
                when {
                    responseBody?.error != null -> {
                        GetRandomUsersResult.Error(responseBody.error)
                    }

                    responseBody?.results != null -> {
                        try {
                            val mappedList = mapper.mapGetRandomUserResponseToUsers(responseBody)
                            GetRandomUsersResult.Success(mappedList)
                        } catch (e: Error) {
                            GetRandomUsersResult.Error(e.toString())
                        }
                    }

                    else -> {
                        GetRandomUsersResult.Error("Unknown error")
                    }
                }
            } else {
                GetRandomUsersResult.Error(response.message())
            }
        } catch (e: IOException) {
            GetRandomUsersResult.Error(e.toString())
        } catch (e: Exception) {
            GetRandomUsersResult.Error(e.toString())
        }
    }
}