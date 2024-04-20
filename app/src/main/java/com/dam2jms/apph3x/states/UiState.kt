package com.dam2jms.apph3x.states
data class UiState(
    val numerosBotones: List<Int> = listOf(1, 3, 4, 6, 7, 8, 9, 10, 25, 50),
    val listaOperaciones : List<String> = listOf("+", "-", "*", "/"),
    val numeroAleatorio: Int = 0,
    val numero1: String = "",
    val numero2: String = "",
    val operacion: String = "",
    var ganador: String = ""
)