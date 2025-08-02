package ru.nyxsed.shifttest.presentation

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.composegears.tiamat.Navigation
import com.composegears.tiamat.rememberNavController
import ru.nyxsed.shifttest.presentation.userinfo.UserInfoScreen
import ru.nyxsed.shifttest.presentation.userslist.UserListScreen
import ru.nyxsed.shifttest.ui.theme.SHIFTtestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController(
                startDestination = UserListScreen,
                destinations = arrayOf(
                    UserListScreen,
                    UserInfoScreen
                )
            )
            SHIFTtestTheme {
                Navigation(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}