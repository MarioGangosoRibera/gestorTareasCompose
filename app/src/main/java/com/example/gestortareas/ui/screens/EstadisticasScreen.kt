package com.example.gestortareas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestortareas.data.Prioridad
import com.example.gestortareas.data.Tarea

@Composable
fun EstadisticasScreen(tareas: List<Tarea>) {
    val totalTareas = tareas.size
    val completadas = tareas.count { it.completada }
    val pendientes = totalTareas - completadas
    val porcentaje = if (totalTareas > 0) (completadas * 100) / totalTareas else 0

    val tareasAlta = tareas.count { it.prioridad == Prioridad.ALTA }
    val tareasMedia = tareas.count { it.prioridad == Prioridad.MEDIA }
    val tareasBaja = tareas.count { it.prioridad == Prioridad.BAJA }

    val completadasAlta = tareas.count { it.prioridad == Prioridad.ALTA && it.completada }
    val completadasMedia = tareas.count { it.prioridad == Prioridad.MEDIA && it.completada }
    val completadasBaja = tareas.count { it.prioridad == Prioridad.BAJA && it.completada }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Estadísticas Detalladas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // Card de resumen general
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Resumen General",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                EstadisticaRow("Total de tareas", totalTareas.toString())
                EstadisticaRow("Completadas", completadas.toString())
                EstadisticaRow("Pendientes", pendientes.toString())
                EstadisticaRow("Porcentaje completado", "$porcentaje%")
            }
        }

        // Card de progreso visual
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "Progreso",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = { porcentaje / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp),
                    color = Color(0xFF43A047),
                    trackColor = Color(0xFFE0E0E0)
                )

                Text(
                    "$completadas de $totalTareas tareas completadas",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Card de estadísticas por prioridad
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Por Prioridad",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                // Alta
                PrioridadProgressBar(
                    titulo = "Alta",
                    color = Prioridad.ALTA.color,
                    total = tareasAlta,
                    completadas = completadasAlta
                )

                // Media
                PrioridadProgressBar(
                    titulo = "Media",
                    color = Prioridad.MEDIA.color,
                    total = tareasMedia,
                    completadas = completadasMedia
                )

                // Baja
                PrioridadProgressBar(
                    titulo = "Baja",
                    color = Prioridad.BAJA.color,
                    total = tareasBaja,
                    completadas = completadasBaja
                )
            }
        }

        // Card de rendimiento
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "🎯 Rendimiento",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    when {
                        porcentaje >= 80 -> "¡Excelente! Sigue así 🌟"
                        porcentaje >= 50 -> "Buen progreso 👍"
                        porcentaje > 0 -> "¡Vamos, tú puedes! 💪"
                        else -> "¡Comienza tu día productivo! 🚀"
                    },
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
fun EstadisticaRow(label: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 16.sp)
        Text(
            valor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun PrioridadProgressBar(
    titulo: String,
    color: Color,
    total: Int,
    completadas: Int
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "$completadas/$total",
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(Color(0xFFE0E0E0), RoundedCornerShape(6.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (total > 0) completadas.toFloat() / total else 0f)
                    .height(12.dp)
                    .background(color, RoundedCornerShape(6.dp))
            )
        }
    }
}