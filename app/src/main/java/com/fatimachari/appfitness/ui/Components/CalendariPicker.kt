package com.fatimachari.appfitness.ui.Components

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendariPicker (
    label: String,
    selectedDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    // Controla si el calendari està obert
    var showDatePicker by remember { mutableStateOf(false)}

    // Convertim la data a un text
    val formattedDate = selectedDateMillis?.let { formatDate(it) } ?: ""

    // Camp de text
    OutlinedTextField(
        value = formattedDate,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        placeholder = { Text("dd/MM/aaaa") },
        trailingIcon = {
            IconButton(
                onClick = { showDatePicker = true }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Seleccionar data"
                )
            }
        },
        modifier = modifier
    )

    // Calendari modal
    if (showDatePicker) {

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDateMillis
        )

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    enabled = datePickerState.selectedDateMillis != null,
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(it)
                            showDatePicker = false
                        }
                    }
                ) {
                    Text("Acceptar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancel·lar")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}

// Funció auxiliar per mostrar la data
private fun formatDate(millis: Long): String {

    val formatter = SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    )

    formatter.timeZone = TimeZone.getTimeZone("UTC")

    return formatter.format(Date(millis))
}
