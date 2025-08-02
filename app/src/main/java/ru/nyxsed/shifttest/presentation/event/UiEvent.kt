package ru.nyxsed.shifttest.presentation.event

import com.composegears.tiamat.NavDestination

/**
 * UI-события, отправляемые из ViewModel
 *
 */
sealed class UiEvent {
    class ShowToast(val message: String) : UiEvent()
    class NavigateBack() : UiEvent()
    class NavigateTo<T>(val destination: NavDestination<T>, val navArgs: T? = null) : UiEvent()
    class SendToApp(val type: IntentAppType, val value: String): UiEvent()
}