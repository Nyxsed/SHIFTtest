package ru.nyxsed.shifttest.di

import androidx.room.Room
import org.koin.dsl.module
import ru.nyxsed.shifttest.data.database.AppDatabase

val databaseModule = module {
    single {
        get<AppDatabase>().DbDao()
    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database")
            .build()
    }
}