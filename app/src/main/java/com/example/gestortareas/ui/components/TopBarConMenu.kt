package com.example.gestortareas.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarConMenu(
    onInfoClick: () -> Unit,
    onToggleModoOscuro: () -> Unit,
    onToggleMostrarCompletadas: () -> Unit
) {
    var menuExpandido by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                "Gestor de Tareas",
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            IconButton(onClick = { /* Buscar */ }) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }

            // Menú de 3 puntos
            Box {
                IconButton(onClick = { menuExpandido = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Más opciones")
                }

                DropdownMenu(
                    expanded = menuExpandido,
                    onDismissRequest = { menuExpandido = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Modo oscuro") },
                        onClick = {
                            onToggleModoOscuro()
                            menuExpandido = false
                        },
                        leadingIcon = {
                            Icon(Icons.Default.DarkMode, contentDescription = null)
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Mostrar completadas") },
                        onClick = {
                            onToggleMostrarCompletadas()
                            menuExpandido = false
                        },
                        leadingIcon = {
                            Icon(Icons.Default.CheckCircle, contentDescription = null)
                        }
                    )

                    Divider()

                    DropdownMenuItem(
                        text = { Text("Configuración") },
                        onClick = {
                            menuExpandido = false
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Settings, contentDescription = null)
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Acerca de") },
                        onClick = {
                            onInfoClick()
                            menuExpandido = false
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Info, contentDescription = null)
                        }
                    )
                }
            }
        }
    )
}