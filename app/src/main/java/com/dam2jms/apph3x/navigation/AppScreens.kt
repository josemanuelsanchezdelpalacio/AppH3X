package com.dam2jms.apph3x.navigation
sealed class AppScreens (val route: String){
    object FirstScreen: AppScreens(route = "first_screen")
}
