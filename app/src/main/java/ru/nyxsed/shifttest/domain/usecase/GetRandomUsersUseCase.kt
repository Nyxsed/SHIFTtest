package ru.nyxsed.shifttest.domain.usecase

import ru.nyxsed.shifttest.data.network.GetRandomUsersResult
import ru.nyxsed.shifttest.domain.repository.RandomRepository
import ru.nyxsed.shifttest.utils.Constants.API_USER_AMOUNT

class GetRandomUsersUseCase(
    private val randomRepository: RandomRepository,
) {
    suspend operator fun invoke(): GetRandomUsersResult<Any> {
        return randomRepository.getRandomUsers(API_USER_AMOUNT)
    }
}