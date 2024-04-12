package com.dam2jms.apph3x.models

import androidx.lifecycle.ViewModel
import com.dam2jms.apph3x.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelJuego : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun actualizarNumeros(numero1: Int, numero2: Int, operacion: String) {
        val resultado = when (operacion) {
            "+" -> numero1 + numero2
            "-" -> if (numero1 >= numero2) numero1 - numero2 else null
            "*" -> numero1 * numero2
            "/" -> if (numero1 % numero2 == 0) numero1 / numero2 else null
            else -> null
        }
        if (resultado != null && resultado > 0) {
            val updatedNumbers = _uiState.value.numerosBotones.filter { it != numero1 && it != numero2 } + resultado
            _uiState.value = _uiState.value.copy(
                numerosBotones = updatedNumbers,
                numero1 = "",
                numero2 = ""
            )
        } else {
            // Handle invalid operation
        }
    }

    fun crearNumeroAleatorio() {
        val numeroAleatorio = (0..999).random()
        _uiState.value = _uiState.value.copy(numeroAleatorio = numeroAleatorio)
    }

    fun changedNumero1(numero1: Int) {
        _uiState.value = _uiState.value.copy(numero1 = numero1.toString())
    }

    fun changedNumero2(numero2: Int) {
        _uiState.value = _uiState.value.copy(numero2 = numero2.toString())
    }

    fun changedOperacion(operacion: String) {
        _uiState.value = _uiState.value.copy(operacion = operacion)
    }

    init {
        crearNumeroAleatorio()
    }
}
