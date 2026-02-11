package com.example.gestortareas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AjustesScreen(
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,
    mostrarCompletadas: Boolean,
    onMostrarCompletadasChange: (Boolean) -> Unit
) {
    var mostrarDialogoAcercaDe by remember { mutableStateOf(false) }
    var notificacionesActivas by remember { mutableStateOf(true) }
    var ordenarPorPrioridad by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Ajustes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // Sección Apariencia
        SeccionAjustes(titulo = "Apariencia") {
            AjusteSwitch(
                icono = Icons.Default.Brightness4,
                titulo = "Modo oscuro",
                descripcion = "Cambiar tema de la aplicación",
                checked = modoOscuro,
                onCheckedChange = onModoOscuroChange
            )
        }

        // Sección Visualización
        SeccionAjustes(titulo = "Visualización") {
            AjusteSwitch(
                icono = Icons.Default.Visibility,
                titulo = "Mostrar completadas",
                descripcion = "Ver tareas completadas en la lista",
                checked = mostrarCompletadas,
                onCheckedChange = onMostrarCompletadasChange
            )

            HorizontalDivider()

            AjusteSwitch(
                icono = Icons.Default.Sort,
                titulo = "Ordenar por prioridad",
                descripcion = "Mostrar primero las de mayor prioridad",
                checked = ordenarPorPrioridad,
                onCheckedChange = { ordenarPorPrioridad = it }
            )
        }

        // Sección Notificaciones
        SeccionAjustes(titulo = "Notificaciones") {
            AjusteSwitch(
                icono = Icons.Default.Notifications,
                titulo = "Notificaciones",
                descripcion = "Recibir recordatorios de tareas",
                checked = notificacionesActivas,
                onCheckedChange = { notificacionesActivas = it }
            )
        }

        // Sección Datos
        SeccionAjustes(titulo = "Datos") {
            AjusteBoton(
                icono = Icons.Default.Delete,
                titulo = "Limpiar completadas",
                descripcion = "Eliminar todas las tareas completadas",
                onClick = { /* Implementar */ }
            )

            HorizontalDivider()

            AjusteBoton(
                icono = Icons.Default.DeleteForever,
                titulo = "Borrar todas las tareas",
                descripcion = "Eliminar toda la información",
                onClick = { /* Implementar */ },
                colorTexto = Color.Red
            )
        }

        // Sección Acerca de
        SeccionAjustes(titulo = "Información") {
            AjusteBoton(
                icono = Icons.Default.Info,
                titulo = "Acerca de",
                descripcion = "Información de la aplicación",
                onClick = { mostrarDialogoAcercaDe = true }
            )

            HorizontalDivider()

            AjusteBoton(
                icono = Icons.Default.Star,
                titulo = "Calificar app",
                descripcion = "Danos tu opinión",
                onClick = { /* Implementar */ }
            )

            HorizontalDivider()

            AjusteBoton(
                icono = Icons.Default.Share,
                titulo = "Compartir app",
                descripcion = "Comparte con tus amigos",
                onClick = { /* Implementar */ }
            )
        }

        // Versión de la app
        Text(
            "Versión 1.0.0",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }

    // Diálogo Acerca de
    if (mostrarDialogoAcercaDe) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoAcercaDe = false },
            title = { Text("Acerca de Gestor de Tareas") },
            text = {
                Column {
                    Text("Versión: 1.0.0")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Desarrollado con Jetpack Compose")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("© 2024 - Todos los derechos reservados")
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarDialogoAcercaDe = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun SeccionAjustes(
    titulo: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Text(
            titulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun AjusteSwitch(
    icono: ImageVector,
    titulo: String,
    descripcion: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icono,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    descripcion,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun AjusteBoton(
    icono: ImageVector,
    titulo: String,
    descripcion: String,
    onClick: () -> Unit,
    colorTexto: Color = Color.Black
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icono,
            contentDescription = null,
            tint = if (colorTexto == Color.Red) colorTexto else MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorTexto
            )
            Text(
                descripcion,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        IconButton(onClick = onClick) {
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = "Ir",
                tint = Color.Gray
            )
        }
    }
}