package ru.nyxsed.shifttest.domain.usecase

import ru.nyxsed.shifttest.domain.repository.DbRepository

class GetDbUsersUseCase(
    private val dbRepository: DbRepository
) {
    operator fun invoke() = dbRepository.getAllUsers()
}