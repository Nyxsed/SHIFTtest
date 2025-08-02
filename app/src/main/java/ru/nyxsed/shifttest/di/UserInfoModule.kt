package ru.nyxsed.shifttest.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.nyxsed.shifttest.domain.models.User
import ru.nyxsed.shifttest.presentation.userinfo.UserInfoViewModel

val userInfoModule = module {
    viewModel { (user: User) ->
        UserInfoViewModel(
            user = user
        )
    }
}