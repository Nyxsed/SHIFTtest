package ru.nyxsed.shifttest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nyxsed.shifttest.data.models.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun DbDao(): DbDao
}