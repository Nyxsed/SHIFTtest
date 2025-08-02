package ru.nyxsed.shifttest.presentation.userinfo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.composegears.tiamat.navArgs
import com.composegears.tiamat.navController
import com.composegears.tiamat.navDestination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.nyxsed.shifttest.R
import ru.nyxsed.shifttest.domain.models.User
import ru.nyxsed.shifttest.presentation.event.CollectUiEvent
import ru.nyxsed.shifttest.presentation.event.IntentAppType

/**
 * Экран информации о пользователе
 *
 */
val UserInfoScreen by navDestination<User> {
    val args = navArgs()
    val userInfoViewModel = koinViewModel<UserInfoViewModel>(
        key = args.phoneNumber,
        parameters = { parametersOf(args) }
    )
    val state by userInfoViewModel.state.collectAsState()
    val navController = navController()

    CollectUiEvent(
        uiEventFlow = userInfoViewModel.uiEventFlow,
        navController = navController,
    )

    UserInfoContent(
        state = state,
        onBackClicked = {
            userInfoViewModel.navigateBack()
        },
        onFieldClicked = { type, value ->
            userInfoViewModel.openIntentApp(type, value)
        }
    )
}

@Composable
fun UserInfoContent(
    state: UserInfoState,
    onBackClicked: () -> Unit,
    onFieldClicked: (IntentAppType, String) -> Unit,
) {
    Scaffold(
        topBar = {
            UserInfoScreenBar(
                onBackClicked = onBackClicked
            )
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .clip(CircleShape)
                        .size(100.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.pictureMediumUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    text = "${state.firstName} ${state.lastName}",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .clickable {
                            onFieldClicked(IntentAppType.Phone, state.phoneNumber)
                        },
                    text = stringResource(R.string.phone, state.phoneNumber),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Left,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .clickable {
                            onFieldClicked(IntentAppType.Email, state.email)
                        },
                    text = stringResource(R.string.email, state.email),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Left,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .clickable {
                            onFieldClicked(IntentAppType.Map, "${state.longitude},${state.latitude}")
                        },
                    text = stringResource(R.string.address, "${state.country}, ${state.state}, ${state.city}, ${state.street}, ${state.house}"),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
}