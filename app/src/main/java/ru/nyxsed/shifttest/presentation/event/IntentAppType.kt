package ru.nyxsed.shifttest.presentation.event

sealed class IntentAppType {
    object Email : IntentAppType()
    object Phone : IntentAppType()
    object Map : IntentAppType()
}