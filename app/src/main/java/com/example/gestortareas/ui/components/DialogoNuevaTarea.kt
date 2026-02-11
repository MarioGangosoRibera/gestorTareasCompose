package com.example.gestortareas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestortareas.data.Prioridad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoNuevaTarea(
    onDismiss: () -> Unit,
    onConfirm: (titulo: String, descripcion: String, prioridad: Prioridad) -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var prioridadSeleccionada by remember { mutableStateOf(Prioridad.MEDIA) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva Tarea") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Campo de título
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Campo de descripción
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )

                // Selector de prioridad
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = prioridadSeleccionada.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Prioridad") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        Prioridad.values().forEach { prioridad ->
                            DropdownMenuItem(
                                text = { Text(prioridad.name) },
                                onClick = {
                                    prioridadSeleccionada = prioridad
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (titulo.isNotBlank()) {
                        onConfirm(titulo, descripcion, prioridadSeleccionada)
                    }
                },
                enabled = titulo.isNotBlank()
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}