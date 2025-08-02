package ru.nyxsed.shifttest.di

import org.koin.dsl.module
import ru.nyxsed.shifttest.data.mapper.RandomUserMapper
import ru.nyxsed.shifttest.data.repository.DbRepositoryImpl
import ru.nyxsed.shifttest.data.repository.RandomRepositoryImpl
import ru.nyxsed.shifttest.domain.repository.DbRepository
import ru.nyxsed.shifttest.domain.repository.RandomRepository

val repositoryModule = module {
    single<RandomUserMapper> {
        RandomUserMapper()
    }

    single<DbRepository>{
        DbRepositoryImpl(
            dbDao = get()
        )
    }

    single<RandomRepository> {
        RandomRepositoryImpl(
            apiService = get(),
            mapper = get()
        )
    }
}