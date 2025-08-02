package ru.nyxsed.shifttest.domain.usecase

import ru.nyxsed.shifttest.domain.models.User
import ru.nyxsed.shifttest.domain.repository.DbRepository

class InsertUsersUseCase(
    private val dbRepository: DbRepository,
) {
    suspend operator fun invoke(users: List<User>) {
        dbRepository.deleteAllUsers()
        users.forEach { user ->
            dbRepository.insertUser(user)
        }
    }
}