
package com.fatimachari.appfitness.ui.screen

import android.icu.util.Calendar
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatimachari.appfitness.R

@Composable
fun ProfileScreen(
    nom: String,
    email: String,
    dataNaixement: String,
    genere: String,
    alçada: Int,
    pes: Double,
    objectiu: String,
    nivellActivitat : String,
    editar: () -> Unit,
) {
    Column {
        // Caixa de foto, nom i email
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null,
                Modifier.size(120.dp).clip(CircleShape).background(Color.Black))

            Text(text = nom, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text(text = email, fontSize = 16.sp, color = Color.Gray)
        }

        // Caixa d'informacion

        Card(Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(10.dp)) {

            Row(Modifier.fillMaxWidth().padding(8.dp),   horizontalArrangement = Arrangement.SpaceBetween) {

                Column() {
                    Text("Edat",  color = Color.Gray)

                    var edat = Calendar.getInstance().get(Calendar.YEAR) - dataNaixement.substring(6).toInt()

                    Text(edat.toString(), fontWeight =  FontWeight.Bold)

                }
                Column() {

                    Text("Genere", color = Color.Gray)

                    Text(genere, fontWeight =  FontWeight.Bold)
                }
                Column() {

                    Text ("Alçada", color = Color.Gray)

                    Text("$alçada cm", fontWeight =  FontWeight.Bold)
                }
                Column() {

                    Text ("Pes", color = Color.Gray)

                    Text("$pes kg ", fontWeight =  FontWeight.Bold)
                }
            }
        }
        // Caixa d'objectius:
        Text("Objectius", Modifier.fillMaxWidth().padding(16.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Card(Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(10.dp)){

            Text(objectiu, Modifier.padding(8.dp))
        }
        // Caixa de nivell d'activitat:
        Text("Nivell d'activitat", Modifier.fillMaxWidth().padding(16.dp),fontSize = 20.sp, fontWeight = FontWeight.Bold, )

        Card(Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(10.dp)){

            Text(nivellActivitat, Modifier.padding(8.dp))
        }
    }
        Column(Modifier.fillMaxWidth().padding(20.dp), horizontalAlignment = Alignment.End ){
        Image(painter = painterResource(id = android.R.drawable.ic_menu_edit), contentDescription = "Edit", alignment = Alignment.TopEnd, modifier = Modifier.size(40.dp).clickable { editar()})
    }
}
