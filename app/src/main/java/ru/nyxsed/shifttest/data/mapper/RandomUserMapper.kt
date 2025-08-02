package ru.nyxsed.shifttest.data.mapper

import ru.nyxsed.shifttest.data.models.response.GetRandomUsersResponse
import ru.nyxsed.shifttest.domain.models.User

class RandomUserMapper {
    fun mapGetRandomUserResponseToUsers(response: GetRandomUsersResponse): List<User> {
        val result = mutableListOf<User>()
        response.results?.forEach {
            val user = User(
                firstName = it.name.first,
                lastName = it.name.last,
                pictureThumbUrl = it.picture.thumbnail,
                pictureMediumUrl = it.picture.medium,
                phoneNumber = it.phone,
                email = it.email,
                country = it.location.country,
                city = it.location.city,
                state = it.location.state,
                street = it.location.street.name,
                house = it.location.street.number,
                latitude = it.location.coordinates.latitude,
                longitude = it.location.coordinates.longitude,
            )
            result.add(user)
        }

        return result
    }
}