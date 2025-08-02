package ru.nyxsed.shifttest.presentation.userslist

import ru.nyxsed.shifttest.domain.models.User

data class UserListState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
)
