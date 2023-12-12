package mx.com.test.android.aero

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint
import mx.com.test.android.presentation.navigation.NavigationGraph
import mx.com.test.android.presentation.screens.FlightViewModel
import mx.com.test.android.presentation.theme.FlightsTheme

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: FlightViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            val postNotificationPermission =
                rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

            LaunchedEffect(key1 = true) {
                if (postNotificationPermission.status.isGranted.not()) {
                    postNotificationPermission.launchPermissionRequest()
                }
            }
            FlightsApp(viewModel)
        }
    }
}

@Composable
fun FlightsApp(viewModel: FlightViewModel) {
    FlightsTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavigationGraph(navController = navController, viewModel = viewModel)
        }
    }
}