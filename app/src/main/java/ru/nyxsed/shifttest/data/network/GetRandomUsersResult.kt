package ru.nyxsed.shifttest.data.network

sealed class GetRandomUsersResult<out T> {
    data class Success<T>(val data: T): GetRandomUsersResult<T>()
    data class Error(val message: String): GetRandomUsersResult<Unit>()
}