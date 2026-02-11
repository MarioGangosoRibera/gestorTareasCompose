package com.example.gestortareas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestortareas.data.Tarea

@Composable
fun SeccionListaTareas(
    tareas: List<Tarea>,
    mostrarCompletadas: Boolean,
    onTareaClick: (Int) -> Unit
) {
    val tareasFiltradas = if (mostrarCompletadas) {
        tareas
    } else {
        tareas.filter { !it.completada }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            "Mis Tareas (${tareasFiltradas.size})",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tareasFiltradas) { tarea ->
                TareaItem(tarea = tarea, onClick = { onTareaClick(tarea.id) })
            }
        }
    }
}