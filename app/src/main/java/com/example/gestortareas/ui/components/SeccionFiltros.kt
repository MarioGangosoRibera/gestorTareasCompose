package com.example.gestortareas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SeccionFiltros() {
    var filtroSeleccionado by remember { mutableStateOf("Todas") }
    val filtros = listOf("Todas", "Alta", "Media", "Baja")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            "Prioridad",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filtros.forEach { filtro ->
                FilterChip(
                    selected = filtroSeleccionado == filtro,
                    onClick = { filtroSeleccionado = filtro },
                    label = { Text(filtro) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}