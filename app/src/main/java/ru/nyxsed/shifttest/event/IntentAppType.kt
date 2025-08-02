package ru.nyxsed.shifttest.event

sealed class IntentAppType {
    object Email : IntentAppType()
    object Phone : IntentAppType()
    object Map : IntentAppType()
}