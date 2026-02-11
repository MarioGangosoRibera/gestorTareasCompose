package com.example.gestortareas.data

import androidx.compose.ui.graphics.Color

// Modelo de datos
data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val prioridad: Prioridad,
    var completada: Boolean = false
)

enum class Prioridad(val color: Color) {
    ALTA(Color(0xFFE53935)),
    MEDIA(Color(0xFFFB8C00)),
    BAJA(Color(0xFF43A047))
}