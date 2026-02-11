package com.example.gestortareas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestortareas.data.Tarea

@Composable
fun SeccionEstadisticas(tareas: List<Tarea>) {
    val totalTareas = tareas.size
    val completadas = tareas.count { it.completada }
    val pendientes = totalTareas - completadas
    val porcentaje = if (totalTareas > 0) (completadas * 100) / totalTareas else 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Resumen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EstadisticaItem("Total", totalTareas.toString(), Color(0xFF1976D2))
                EstadisticaItem("Pendientes", pendientes.toString(), Color(0xFFFB8C00))
                EstadisticaItem("Completadas", completadas.toString(), Color(0xFF43A047))
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = porcentaje / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFF43A047)
            )

            Text(
                "Progreso: $porcentaje%",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun EstadisticaItem(label: String, valor: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                valor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp)
    }
}