package ru.nyxsed.shifttest.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.nyxsed.shifttest.presentation.userslist.UserListViewModel

val userListModule = module {
    viewModel {
        UserListViewModel(
            getRandomUsersUseCase = get(),
            insertUsersUseCase = get(),
            getDbUsersUseCase = get(),
        )
    }
}