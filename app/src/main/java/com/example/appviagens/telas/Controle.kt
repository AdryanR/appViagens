package com.example.appviagens.telas

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.appviagens.ScreenManager

@Composable
fun Controle() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenManager.Login.route) {

        composable(ScreenManager.Login.route) {
            EstadoLogin(navController = navController)
        }

        composable(ScreenManager.Cadastro.route) {
            CadastroCompose(navController = navController)
        }

//        composable(ScreenManager.EsqueciSenha.route) { navBackStack ->
//            EsqueciSenhaCompose(navController = navController)
//        }

        composable("home/{nameUser}/{UserID}",
            arguments = listOf(
                navArgument("nameUser") {
                    type = NavType.StringType
                },
                navArgument("UserID") {
                    type = NavType.IntType
                }
            )

        ) {
            val nameUser = it.arguments?.getString("nameUser")
            val UserID = it.arguments?.getInt("UserID")
            if (nameUser != null && UserID != null) {
                HomeNavigation(nameUser, UserID)
            }
        }
    }
}