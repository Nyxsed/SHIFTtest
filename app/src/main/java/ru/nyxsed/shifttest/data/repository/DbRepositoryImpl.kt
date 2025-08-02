package ru.nyxsed.shifttest.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.nyxsed.shifttest.data.database.DbDao
import ru.nyxsed.shifttest.data.mapper.UserMapper.toDomain
import ru.nyxsed.shifttest.data.mapper.UserMapper.toEntity
import ru.nyxsed.shifttest.domain.models.User
import ru.nyxsed.shifttest.domain.repository.DbRepository

class DbRepositoryImpl(
    private val dbDao: DbDao
): DbRepository {
    val scope = CoroutineScope(Dispatchers.Default)

    override fun getAllUsers(): StateFlow<List<User>> =
        dbDao.getAllUsers()
            .map { entityList->
                entityList.map { userEntity ->
                    userEntity.toDomain()
                }
            }
            .stateIn(
                scope = scope,
                started = SharingStarted.Eagerly,
                initialValue = listOf()
            )

    override suspend fun deleteAllUsers() {
        dbDao.deleteAllUsers()
    }

    override suspend fun insertUser(user: User) {
        dbDao.insertUser(user.toEntity())
    }
}