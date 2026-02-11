package com.example.gestortareas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gestortareas.data.Pantalla
import com.example.gestortareas.data.Prioridad
import com.example.gestortareas.data.Tarea
import com.example.gestortareas.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestorTareasScreen() {
    var mostrarDialogoInfo by remember { mutableStateOf(false) }
    var modoOscuro by remember { mutableStateOf(false) }
    var mostrarCompletadas by remember { mutableStateOf(true) }
    var mostrarDialogoNuevaTarea by remember { mutableStateOf(false) }
    var siguienteId by remember { mutableStateOf(6) }
    // Variable que guarda qué pantalla está seleccionada
    var pantallaSeleccionada by remember { mutableStateOf(Pantalla.Tareas.ruta) }

    // Lista de tareas de ejemplo
    var listaTareas by remember {
        mutableStateOf(
            listOf(
                Tarea(1, "Estudiar Kotlin", "Repasar Compose y StateFlow", Prioridad.ALTA),
                Tarea(2, "Hacer ejercicio", "30 minutos de cardio", Prioridad.MEDIA),
                Tarea(3, "Comprar comida", "Ir al supermercado", Prioridad.BAJA),
                Tarea(4, "Leer libro", "Continuar con Clean Code", Prioridad.MEDIA, true),
                Tarea(5, "Proyecto final", "Terminar la app Android", Prioridad.ALTA)
            )
        )
    }

    Scaffold(
        topBar = {
            TopBarConMenu(
                onInfoClick = { mostrarDialogoInfo = true },
                onToggleModoOscuro = { modoOscuro = !modoOscuro },
                onToggleMostrarCompletadas = { mostrarCompletadas = !mostrarCompletadas }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarDialogoNuevaTarea = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = "Tareas") },
                    label = { Text("Tareas") },
                    selected = pantallaSeleccionada == Pantalla.Tareas.ruta,
                    onClick = { pantallaSeleccionada = Pantalla.Tareas.ruta }
                )

                // Item 2: Estadísticas
                NavigationBarItem(
                    icon = { Icon(Icons.Default.BarChart, contentDescription = "Estadísticas") },
                    label = { Text("Estadísticas") },
                    selected = pantallaSeleccionada == Pantalla.Estadisticas.ruta,
                    onClick = { pantallaSeleccionada = Pantalla.Estadisticas.ruta }
                )

                // Item 3: Ajustes
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Ajustes") },
                    label = { Text("Ajustes") },
                    selected = pantallaSeleccionada == Pantalla.Ajustes.ruta,
                    onClick = { pantallaSeleccionada = Pantalla.Ajustes.ruta }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (pantallaSeleccionada) {

                Pantalla.Tareas.ruta -> {
                    Column {
                        SeccionEstadisticas(listaTareas)

                        SeccionFiltros()

                        SeccionListaTareas(
                            tareas = listaTareas,
                            mostrarCompletadas = mostrarCompletadas,
                            onTareaClick = { tareaId ->
                                listaTareas = listaTareas.map {
                                    if (it.id == tareaId)
                                        it.copy(completada = !it.completada)
                                    else it
                                }
                            }
                        )
                    }
                }

                Pantalla.Estadisticas.ruta -> {
                    EstadisticasScreen(tareas = listaTareas)
                }

                Pantalla.Ajustes.ruta -> {
                    AjustesScreen(
                        modoOscuro = modoOscuro,
                        onModoOscuroChange = { modoOscuro = it },
                        mostrarCompletadas = mostrarCompletadas,
                        onMostrarCompletadasChange = { mostrarCompletadas = it }
                    )
                }
            }
        }
    }
        // Diálogo de información
    if (mostrarDialogoInfo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoInfo = false },
            title = { Text("Acerca de la App") },
            text = {
                Text("Gestor de Tareas v1.0\n\nDesarrollado con Jetpack Compose\n\n© 2024")
            },
            confirmButton = {
                TextButton(onClick = { mostrarDialogoInfo = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
    //Dialogo nueva tarea
    if (mostrarDialogoNuevaTarea) {
        DialogoNuevaTarea(
            onDismiss = { mostrarDialogoNuevaTarea = false },
            onConfirm = { titulo, descripcion, prioridad ->
                val nuevaTarea = Tarea(
                    id = siguienteId,
                    titulo = titulo,
                    descripcion = descripcion,
                    prioridad = prioridad,
                    completada = false
                )
                listaTareas = listaTareas + nuevaTarea
                siguienteId++
                mostrarDialogoNuevaTarea = false
            }
        )
    }
}
