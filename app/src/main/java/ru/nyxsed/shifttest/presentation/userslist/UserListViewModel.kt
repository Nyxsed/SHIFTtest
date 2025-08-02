package ru.nyxsed.shifttest.presentation.userslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nyxsed.shifttest.data.network.GetRandomUsersResult
import ru.nyxsed.shifttest.domain.models.User
import ru.nyxsed.shifttest.domain.usecase.GetDbUsersUseCase
import ru.nyxsed.shifttest.domain.usecase.GetRandomUsersUseCase
import ru.nyxsed.shifttest.domain.usecase.InsertUsersUseCase
import ru.nyxsed.shifttest.presentation.event.UiEvent
import ru.nyxsed.shifttest.presentation.userinfo.UserInfoScreen

class UserListViewModel(
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val insertUsersUseCase: InsertUsersUseCase,
    private val getDbUsersUseCase: GetDbUsersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UserListState())
    val state = _state.asStateFlow()

    private val _uiEventFlow = MutableSharedFlow<UiEvent>(replay = 0, extraBufferCapacity = 1)
    val uiEventFlow: SharedFlow<UiEvent> = _uiEventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getDbUsersUseCase()
                .collect { listUsers ->
                    _state.update { it.copy(users = listUsers) }
                }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val response = getRandomUsersUseCase()
            when (response) {
                is GetRandomUsersResult.Success -> {
                    insertUsersUseCase(response.data as List<User>)
                }
                is GetRandomUsersResult.Error -> {
                    _uiEventFlow.emit(UiEvent.ShowToast(response.message))
                }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun itemClicked(user: User) {
        viewModelScope.launch {
            _uiEventFlow.emit(UiEvent.NavigateTo(UserInfoScreen, user))
        }
    }
}