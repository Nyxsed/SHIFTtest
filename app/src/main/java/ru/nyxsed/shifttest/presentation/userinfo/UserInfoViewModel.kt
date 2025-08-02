package ru.nyxsed.shifttest.presentation.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nyxsed.shifttest.domain.models.User
import ru.nyxsed.shifttest.presentation.event.IntentAppType
import ru.nyxsed.shifttest.presentation.event.UiEvent

class UserInfoViewModel(
    private val user: User,
) : ViewModel() {
    private val _state = MutableStateFlow(UserInfoState())
    val state = _state.asStateFlow()

    private val _uiEventFlow = MutableSharedFlow<UiEvent>(replay = 0, extraBufferCapacity = 1)
    val uiEventFlow: SharedFlow<UiEvent> = _uiEventFlow.asSharedFlow()

    init {
        _state.update {
            it.copy(
                firstName = user.firstName,
                lastName = user.lastName,
                pictureMediumUrl = user.pictureMediumUrl,
                phoneNumber = user.phoneNumber,
                email = user.email,
                country = user.country,
                city = user.city,
                state = user.state,
                street = user.street,
                house = user.house,
                latitude = user.latitude,
                longitude = user.longitude,
            )
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _uiEventFlow.emit(UiEvent.NavigateBack())
        }
    }

    fun openIntentApp(type: IntentAppType, value: String) {
        viewModelScope.launch {
            _uiEventFlow.emit(UiEvent.SendToApp(type, value))
        }
    }

}