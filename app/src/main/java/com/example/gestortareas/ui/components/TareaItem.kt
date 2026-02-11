package com.example.gestortareas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
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
fun TareaItem(tarea: Tarea, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tarea.completada)
                Color(0xFFE8F5E9)
            else
                Color.White
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador de prioridad
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(tarea.prioridad.color)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    tarea.titulo,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = if (tarea.completada) Color.Gray else Color.Black
                )
                Text(
                    tarea.descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            if (tarea.completada) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Completada",
                    tint = Color(0xFF43A047)
                )
            } else {
                Icon(
                    Icons.Default.RadioButtonUnchecked,
                    contentDescription = "Pendiente",
                    tint = Color.Gray
                )
            }
        }
    }
}
