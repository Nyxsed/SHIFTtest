package ru.nyxsed.shifttest.data.mapper

import ru.nyxsed.shifttest.data.models.entity.UserEntity
import ru.nyxsed.shifttest.domain.models.User

object UserMapper {
    fun UserEntity.toDomain(): User {
        return User(
            firstName = firstName,
            lastName = lastName,
            pictureThumbUrl = pictureThumbUrl,
            pictureMediumUrl = pictureMediumUrl,
            phoneNumber = phoneNumber,
            email = email,
            country = country,
            city = city,
            state = state,
            street = street,
            house = house,
            latitude = latitude,
            longitude = longitude,
        )
    }

    fun User.toEntity(): UserEntity {
        return UserEntity(
            firstName = firstName,
            lastName = lastName,
            pictureThumbUrl = pictureThumbUrl,
            pictureMediumUrl = pictureMediumUrl,
            phoneNumber = phoneNumber,
            email = email,
            country = country,
            city = city,
            state = state,
            street = street,
            house = house,
            latitude = latitude,
            longitude = longitude,
        )
    }
}