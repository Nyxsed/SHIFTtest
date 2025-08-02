package ru.nyxsed.shifttest.di

import org.koin.dsl.module
import ru.nyxsed.shifttest.domain.usecase.GetDbUsersUseCase
import ru.nyxsed.shifttest.domain.usecase.GetRandomUsersUseCase
import ru.nyxsed.shifttest.domain.usecase.InsertUsersUseCase

val useCaseModule = module {
    factory { GetRandomUsersUseCase(get()) }
    factory { InsertUsersUseCase(get()) }
    factory { GetDbUsersUseCase(get()) }
}