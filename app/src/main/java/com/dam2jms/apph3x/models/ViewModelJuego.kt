package com.dam2jms.apph3x.models

import androidx.lifecycle.ViewModel
import com.dam2jms.apph3x.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelJuego : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun changedNumero1(numero1: Int) {
        _uiState.value = _uiState.value.copy(numero1 = numero1.toString())
    }

    fun changedNumero2(numero2: Int) {
        _uiState.value = _uiState.value.copy(numero2 = numero2.toString())
    }

    fun changedOperacion(operacion: String) {
        _uiState.value = _uiState.value.copy(operacion = operacion)
    }

    fun actualizarNumeros(numero1: Int, numero2: Int, operacion: String): UiState {
        //hago las operaciones segun la opcion que se seleccione
        val resultado = when (operacion) {
            "+" -> numero1 + numero2
            "-" -> if (numero1 >= numero2) numero1 - numero2 else null
            "*" -> numero1 * numero2
            "/" -> if (numero1 % numero2 == 0) numero1 / numero2 else null
            else -> null
        }

        //si el resultado de la operacion no es nulo y es mayor o igual a 0
        var numerosActualizados = _uiState.value.numerosBotones
        if (resultado != null && resultado > 0) {
            //filtro los numeros usados en la operacion
            numerosActualizados = _uiState.value.numerosBotones.filter {
                it != numero1 && it != numero2
            } + resultado //y pongo el resultado a la lista de numeros de los botones
        }

        var ganador = ""
        if (resultado == _uiState.value.numeroAleatorio) {
            ganador = "Has ganado.\nHas llegado al número objetivo."
        }

        return _uiState.value.copy(
            numerosBotones = numerosActualizados,
            //pongo los numeros vacios para que se quite el boton
            numero1 = "",
            numero2 = "",
            ganador = ganador
        )
    }

    fun reiniciarJuego() {
        _uiState.value = _uiState.value.copy(numero1 = "", numero2 = "", operacion = "")
        crearNumeroAleatorio()
        _uiState.value = _uiState.value.copy(numerosBotones = listOf(1, 3, 4, 6, 7, 8, 9, 10, 25, 50))
    }

    //funcion para crear un numero aleatorio entre 0 y 999
    fun crearNumeroAleatorio() {
        val numeroAleatorio = (0..999).random()
        _uiState.value = _uiState.value.copy(numeroAleatorio = numeroAleatorio)
    }

    fun borrarNumero1() {
        _uiState.value = _uiState.value.copy(numero1 = "")
    }

    fun borrarNumero2() {
        _uiState.value = _uiState.value.copy(numero2 = "")
    }
}
