package com.dam2jms.apph3x.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2jms.apph3x.models.ViewModelJuego
import com.dam2jms.apph3x.screens.InstruccionesScreen
import com.dam2jms.apph3x.screens.JuegoScreen

@Composable
fun appNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.InstruccionesScreen.route) {
        composable(route = AppScreens.InstruccionesScreen.route) { InstruccionesScreen(navController) }
        composable(route = AppScreens.JuegoScreen.route) { JuegoScreen(navController, mvvm = ViewModelJuego()) }
    }
}
