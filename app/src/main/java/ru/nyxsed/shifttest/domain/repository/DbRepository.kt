package ru.nyxsed.shifttest.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.nyxsed.shifttest.domain.models.User

interface DbRepository {
    fun getAllUsers(): StateFlow<List<User>>
    suspend fun deleteAllUsers()
    suspend fun insertUser(user: User)
}