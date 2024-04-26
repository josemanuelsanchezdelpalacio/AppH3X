package com.dam2jms.apph3x.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam2jms.apph3x.navigation.AppScreens
import kotlin.system.exitProcess

@Composable
fun InstruccionesScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        //tarjeta con las instrucciones
        Card(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Instrucciones del juego:")
                Text(text = "Deberas llegar al numero objetivo haciendo calculos con los numeros proporcionados.")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.navigate(route = AppScreens.JuegoScreen.route) }) {
                Text("Jugar")
            }

            Button(onClick = { exitProcess(0) }) {
                Text("Salir")
            }
        }
    }
}
