package com.fatimachari.appfitness.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fatimachari.appfitness.R
import com.fatimachari.appfitness.ui.Components.CalendariPicker
import com.fatimachari.appfitness.ui.Components.MenuDesplegable
import java.io.BufferedReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun EditProfileScreen(
    nom: String,
    email: String,
    dataNaixement: String,
    genere: String,
    alçada: Int,
    pes: Double,
    pesObjectiu: Double,
    objectiu: String,
    nivellActivitat: String,
    guardar: (String, String, String, String, Int, Double, Double, String, String) -> Unit
) {
    var nomNou: String by remember { mutableStateOf(nom) }
    var emailNou: String by remember { mutableStateOf(email) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(parseDateString(dataNaixement)) }
    var dataNaixementNova: String by remember { mutableStateOf(selectedDateMillis?.let { formatMillis(it) } ?: dataNaixement) }
    var genereNou: String by remember { mutableStateOf(genere) }
    var alçadaNou: Int by remember { mutableStateOf(alçada) }
    var pesNou: Double by remember { mutableStateOf(pes) }
    var pesObjectiuNou by remember { mutableStateOf(pesObjectiu) }
    var objectiuNou: String by remember { mutableStateOf(objectiu) }
    var nivellActivitatNou: String by remember { mutableStateOf(nivellActivitat) }
    val opcionGenere = listOf("Masculí", "Femení", "Prefereixo no dir-ho")
    val opcionObjectiu = listOf("Guanyar massa muscular", "Redueix pes", "Manté el meu pes")

    Column {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Black)

            )
            Column(Modifier.fillMaxWidth().padding(end = 8.dp), horizontalAlignment = Alignment.End) {
                Button(onClick = {}) { Text(text = "Canviar foto") }
                Button(onClick = {}) { Text(text = "Eliminar foto") }
            }
        }
        Text(text = "Dades personals",fontWeight = FontWeight.Bold, modifier = Modifier
            .padding(top = 16.dp, start = 8.dp))

        OutlinedTextField(
            value = nomNou,
            onValueChange = { nomNou = it },
            label = { Text("Nom complet") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = emailNou,
            onValueChange = { emailNou = it },
            label = { Text("Email") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        CalendariPicker(
            label = "Data de naixement",
            selectedDateMillis = selectedDateMillis,
            onDateSelected = { millis ->
                selectedDateMillis = millis
                dataNaixementNova = formatMillis(millis)
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        MenuDesplegable(
            label = "Gènere",
            opcions = opcionGenere,
            valorSeleccionat = genereNou,
            onValorSeleccionat = { genereNou = it },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        Text(text = "Dades físiques",fontWeight = FontWeight.Bold, modifier = Modifier
            .padding(10.dp))

        Row {
            OutlinedTextField(
                value = alçadaNou.toString(),
                onValueChange = {
                    alçadaNou = it.toIntOrNull() ?: 0
                },
                label = { Text("Alçada (cm)") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 4.dp)
            )

            OutlinedTextField(
                value = pesNou.toString(),
                onValueChange = {
                    pesNou = it.toDoubleOrNull() ?: 0.0 },
                label = { Text("Pes (kg)") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp , end = 4.dp)
            )

            OutlinedTextField(
                value = pesObjectiuNou.toString(),
                onValueChange = {
                    pesObjectiuNou = it.toDoubleOrNull() ?: 0.0
                },
                label = { Text("Objectiu (kg)") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp, end = 8.dp)
            )
        }

        Text(
            text = "Objectiu principal",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = 18.dp,
                top = 18.dp,
                end = 18.dp,
                bottom = 8.dp
            )
        )
        MenuDesplegable(
            label = "Selecciona el teu objectiu",
            opcions = opcionObjectiu,
            valorSeleccionat = objectiuNou,
            onValorSeleccionat = { objectiuNou = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
        )

        Button(
            onClick = {
                guardar(nomNou, emailNou, dataNaixementNova, genereNou, alçadaNou, pesNou,
                    pesObjectiuNou, objectiuNou, nivellActivitatNou) },
            modifier = Modifier.fillMaxWidth().padding(18.dp)
        ) {
            Text(text = "Guardar dades")
        }
    }
}

private fun parseDateString(dateStr: String): Long? {
    return try {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        formatter.parse(dateStr)?.time
    } catch (_: Exception) {
        null
    }
}

private fun formatMillis(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(Date(millis))
}
