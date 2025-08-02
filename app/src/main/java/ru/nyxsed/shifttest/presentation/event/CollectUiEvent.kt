package ru.nyxsed.shifttest.presentation.event

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.composegears.tiamat.NavController
import com.composegears.tiamat.NavDestination
import kotlinx.coroutines.flow.Flow

/**
 * Собирает UI-события из ViewModel и выполняет побочные действия (тосты, навигация)
 * Вызывается один раз на экране
 *
 */
@Composable
fun CollectUiEvent(
    uiEventFlow: Flow<UiEvent>,
    navController: NavController? = null,
) {
    val context = LocalContext.current

    LaunchedEffect(uiEventFlow) {
        uiEventFlow.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

                is UiEvent.NavigateBack -> {
                    navController?.back()
                }


                is UiEvent.NavigateTo<*> -> {
                    val destination = event.destination as NavDestination<Any?>
                    navController?.navigate(destination, event.navArgs)
                }

                is UiEvent.SendToApp -> {

                    when (event.type) {
                        IntentAppType.Email -> {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:${event.value}")
                            }
                            context.startActivity(intent)
                        }

                        IntentAppType.Map -> {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("geo:${event.value}")
                            }
                            context.startActivity(intent)
                        }

                        IntentAppType.Phone -> {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${event.value}")
                            }
                            context.startActivity(intent)
                        }
                    }

                }
            }
        }
    }
}