package ru.nyxsed.shifttest.domain.repository

import ru.nyxsed.shifttest.data.network.GetRandomUsersResult
import ru.nyxsed.shifttest.domain.models.User

interface RandomRepository {
    suspend fun getRandomUsers(userAmount: Int): GetRandomUsersResult<Any>
}