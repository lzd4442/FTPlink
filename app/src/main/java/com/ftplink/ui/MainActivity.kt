package com.ftplink.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ftplink.ui.screens.*
import com.ftplink.ui.theme.FTPlinkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FTPlinkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FTPlinkNavHost()
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object ServerList : Screen("server_list")
    object AddServer : Screen("add_server")
    object FileBrowser : Screen("file_browser/{serverId}") {
        fun createRoute(serverId: Long) = "file_browser/$serverId"
    }
    object Settings : Screen("settings")
}

@Composable
fun FTPlinkNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ServerList.route
    ) {
        composable(Screen.ServerList.route) {
            ServerListScreen(
                onNavigateToAddServer = { navController.navigate(Screen.AddServer.route) },
                onNavigateToFileBrowser = { serverId ->
                    navController.navigate(Screen.FileBrowser.createRoute(serverId))
                },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.AddServer.route) {
            AddServerScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.FileBrowser.route,
            arguments = listOf(navArgument("serverId") { type = NavType.LongType })
        ) { backStackEntry ->
            val serverId = backStackEntry.arguments?.getLong("serverId") ?: 0L
            FileBrowserScreen(
                serverId = serverId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
