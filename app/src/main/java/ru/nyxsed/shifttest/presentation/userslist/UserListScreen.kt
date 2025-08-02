package ru.nyxsed.shifttest.presentation.userslist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composegears.tiamat.navController
import com.composegears.tiamat.navDestination
import org.koin.androidx.compose.koinViewModel
import ru.nyxsed.shifttest.R
import ru.nyxsed.shifttest.domain.models.User
import ru.nyxsed.shifttest.event.CollectUiEvent

/**
 * Экран пользователей, входная точка приложения
 * Отображет кешированные данные, позволяет обновлять список пользователей
 *
 */
val UserListScreen by navDestination<Unit> {
    val userListViewModel = koinViewModel<UserListViewModel>()
    val state by userListViewModel.state.collectAsState()
    val navController = navController()

    CollectUiEvent(
        uiEventFlow = userListViewModel.uiEventFlow,
        navController = navController,
    )

    UserListContent(
        state = state,
        onRefreshClicked = { userListViewModel.refreshUsers() },
        onItemClicked = {
            userListViewModel.itemClicked(it)
        }
    )
}

@Composable
fun UserListContent(
    state: UserListState,
    onRefreshClicked: () -> Unit,
    onItemClicked: (User) -> Unit,
) {
    Scaffold(
        topBar = {
            UserListScreenBar(
                onRefreshClicked = onRefreshClicked
            )
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            if (state.users.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_data_found),
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddings),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(
                        items = state.users,
                        key = { it.phoneNumber }
                    ) {
                        Box(modifier = Modifier
                            .clickable {
                                onItemClicked(it)
                            }) {
                            UserListCard(user = it)
                        }
                    }
                }
            }
        }
    }
}