package com.dam2jms.apph3x.navigation
sealed class AppScreens (val route: String){
    object InstruccionesScreen: AppScreens(route = "instrucciones_screen")

    object JuegoScreen: AppScreens(route = "juego_screen")
}
