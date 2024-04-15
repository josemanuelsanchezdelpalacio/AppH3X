package com.dam2jms.apph3x.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam2jms.apph3x.models.ViewModelJuego
import com.dam2jms.apph3x.states.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController, mvvm: ViewModelJuego) {

    val uiState by mvvm.uiState.collectAsState()
    //genero el numero aleatorio
    mvvm.crearNumeroAleatorio()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "H3X") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        h3xBodyScreen(modifier = Modifier.padding(paddingValues), mvvm, uiState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun h3xBodyScreen(modifier: Modifier, viewModel: ViewModelJuego, uiState: UiState) {

    val context = LocalContext.current

    //lista con las opciones de las operaciones
    val listaOperaciones : List<String> = listOf("+", "-", "*", "/")
    //variable para controlar la abertura y cierre del menu
    var expanded by remember { mutableStateOf(false) }
    //variable para almacenar la opcion seleccionada del menu desplegable de operaciones
    var opcionSeleccionada by remember { mutableStateOf(listaOperaciones[0]) }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        //muestro por pantalla el numero aleatorio generado
        Text(text = "Numero objetivo: ${uiState.numeroAleatorio}", fontSize = 24.sp)

        Column {
            //creo dos filas con 5 botones en cada una
            uiState.numerosBotones.chunked(5).forEach { fila ->
                Row {
                    //recorro cada fila y les pongo los numeros
                    fila.forEach { numero ->
                        Button(
                            onClick = {
                                //para seleccionar los numeros.
                                if (uiState.numero1.isEmpty()) {
                                    viewModel.changedNumero1(numero)
                                } else if (uiState.numero2.isEmpty()) {
                                    viewModel.changedNumero2(numero)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(Color(0xFF008000))
                        ) {
                            Text(text = numero.toString())
                        }
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        //primer textfield con el primer numero seleccionado
        TextField(value = uiState.numero1, onValueChange = {}, readOnly = true)

        Spacer(modifier = Modifier.height(16.dp))

        //creo la lista desplegable de las operaciones
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                TextField(
                    value = opcionSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = !expanded }) {
                    listaOperaciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(text = opcion) },
                            onClick = {
                                opcionSeleccionada = opcion
                                viewModel.changedOperacion(opcion)
                                expanded = false
                                Toast.makeText(context, "Has elegido la $opcion", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //segundo textfield con el segundo numero seleccionado
        TextField(value = uiState.numero2, onValueChange = {}, readOnly = true)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.actualizarNumeros(uiState.numero1.toInt(), uiState.numero2.toInt(), uiState.operacion)
        }) {
            Text("Calcular")
        }
    }
}