
package com.fatimachari.appfitness.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatimachari.appfitness.R
import java.util.Calendar
import java.util.TimeZone
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ProfileScreen(
    nom: String,
    email: String,
    dataNaixement: String,
    genere: String,
    alçada: Int,
    pes: Double,
    objectiu: String,
    nivellActivitat: String,
    editar: () -> Unit,
) {
    Column(modifier = Modifier.padding(top = 16.dp)) {

        // Caixa de foto, nom i email
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            )

            Text(
                text = nom,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = email,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        // Caixa d'informacio
        Card(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Edat", color = Color.Gray)

                    // Convertir la data String a Long
                    val dataEnMillis = parseDateString(dataNaixement) ?: 0L

                    // Calcular edat amb el metode del professor
                    val edat = calcularEdat(dataEnMillis)

                    Text(
                        edat.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }

                Column {
                    Text("Genere", color = Color.Gray)
                    Text(genere, fontWeight = FontWeight.Bold)
                }

                Column {
                    Text("Alçada", color = Color.Gray)
                    Text("$alçada cm", fontWeight = FontWeight.Bold)
                }

                Column {
                    Text("Pes", color = Color.Gray)
                    Text("$pes kg", fontWeight = FontWeight.Bold)
                }
            }
        }

        // Caixa d'objectius
        Text(
            "Objectius",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )

        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(objectiu, Modifier.padding(8.dp))
        }

        // Caixa de nivell d'activitat
        Text(
            "Nivell d'activitat",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )

        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(nivellActivitat, Modifier.padding(8.dp))
        }
    }

    // Botó per editar el perfil
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_edit),
            contentDescription = "Edit",
            alignment = Alignment.TopEnd,
            modifier = Modifier
                .size(64.dp)
                .clickable {
                    editar()
                }
        )
    }
}

/*
 * Convertir la data de naixement String a Long (mil·lisegons)
 * Format: dd/MM/yyyy
 */
private fun parseDateString(dateStr: String): Long? {
    return try {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        formatter.isLenient = false

        formatter.parse(dateStr)?.time

    } catch (e: Exception) {
        null
    }
}

/*
 * Funcio del professor per calcular l'edat
 * @param dataNaixement Data de naixement en format Date millis
 */
private fun calcularEdat(dataNaixement: Long): Int {
    if (dataNaixement <= 0L) return 0

    return try {
        val calNaixement = Calendar.getInstance(
            TimeZone.getTimeZone("UTC")
        ).apply {
            timeInMillis = dataNaixement
        }

        val avui = Calendar.getInstance()

        val anyActual = avui.get(Calendar.YEAR)
        val mesActual = avui.get(Calendar.MONTH)
        val diaActual = avui.get(Calendar.DAY_OF_MONTH)

        val anyNaixement = calNaixement.get(Calendar.YEAR)
        val mesNaixement = calNaixement.get(Calendar.MONTH)
        val diaNaixement = calNaixement.get(Calendar.DAY_OF_MONTH)

        var edat = anyActual - anyNaixement

        // Si encara no ha fet els anys aquest any, restem 1
        if (
            mesActual < mesNaixement ||
            (mesActual == mesNaixement && diaActual < diaNaixement)
        ) {
            edat--
        }

        if (edat < 0) 0 else edat

    } catch (e: Exception) {
        0
    }
}
