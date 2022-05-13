package com.example.appviagens.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FlightTakeoff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appviagens.ScreenManager
import com.example.appviagens.component.AppBarTelas

@Composable
fun HomeCompose() {
    Scaffold(
        topBar = { AppBarTelas("Bem vindo", Icons.Rounded.FlightTakeoff) }
    ) {
        Text(
            text = "Home",
            fontSize = 30.sp,
            color = Color.Black
        )
    }
}

@Composable
fun HomeNavigation() {

    val navController = rememberNavController()
    val items = listOf(
        ScreenManager.Home,
        ScreenManager.Viagens,
        ScreenManager.Sobre
    )
    Scaffold(
        bottomBar = {
            androidx.compose.material.BottomNavigation(
                backgroundColor = MaterialTheme.colors.secondary,
//        contentColor = Color.Black
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = {
                            Text(
                                text = stringResource(item.resourceId),
                                fontSize = 12.sp
                            )
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Black.copy(0.4f),
                        alwaysShowLabel = true,
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {

                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenManager.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(ScreenManager.Home.route) { HomeCompose() }
            composable(ScreenManager.Viagens.route) { ViagensCompose(navController = navController) }
            composable(ScreenManager.Sobre.route) { SobreCompose() }
//            composable(ScreenManager.FormViagem.route) { FormViagem(navController = navController, 0) }
            formViagemGrap(navController)
        }
    }
}

