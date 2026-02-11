package com.example.gestortareas.data

sealed class Pantalla(val ruta: String, val titulo: String) {
    object Tareas : Pantalla("tareas", "Tareas")
    object Estadisticas : Pantalla("estadisticas", "Estadísticas")
    object Ajustes : Pantalla("ajustes", "Ajustes")
}