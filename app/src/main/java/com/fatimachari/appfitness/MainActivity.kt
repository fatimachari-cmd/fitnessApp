
package com.fatimachari.appfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fatimachari.appfitness.ui.screen.EditProfileScreen
import com.fatimachari.appfitness.ui.screen.ProfileScreen
import com.fatimachari.appfitness.ui.screen.RutineScreen
import com.fatimachari.appfitness.ui.screen.WeightTrackingScreen
import com.fatimachari.appfitness.ui.theme.AppFitnessTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppFitnessTheme {
                FitnessApp()
            }
        }
    }
}

@Composable
fun FitnessApp() {

    // Pestanya seleccionada a la barra de baix (0 = Perfil, 1 = Pes, 2 = Rutina)
    var pantalla by remember { mutableIntStateOf(0) }

    var nom by remember { mutableStateOf("Laia Soler") }
    var email by remember { mutableStateOf("Laia.soler@gmail.com") }
    var dataNaixement by remember { mutableStateOf("17/05/2001") }
    var genere by remember { mutableStateOf("Dona") }

    var alcada by remember { mutableIntStateOf(160) }
    var pes by remember { mutableDoubleStateOf(55.5) }

    var objectiu by remember {
        mutableStateOf("Guanyar massa muscular")
    }

    var pesObjectiu by remember { mutableDoubleStateOf(0.0) }

    var nivellActivitat by remember {
        mutableStateOf("Moderat")
    }

    var editant by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = pantalla == 0,
                    onClick = { pantalla = 0 },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
                NavigationBarItem(
                    selected = pantalla == 1,
                    onClick = { pantalla = 1 },
                    icon = { Icon(Icons.Default.MonitorWeight, contentDescription = "Pes") },
                    label = { Text("Pes") }
                )
                NavigationBarItem(
                    selected = pantalla == 2,
                    onClick = { pantalla = 2 },
                    icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Rutina") },
                    label = { Text("Rutina") }
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (pantalla) {
                0 -> if (editant) {
                    EditProfileScreen(
            nom,
            email,
            dataNaixement,
            genere,
            alcada,
            pes,
            pesObjectiu,
            objectiu,
            nivellActivitat
        ) { nomNou, emailNou, dataNaixementNova,
            genereNou, alcadaNova, pesNou, pesObjectiuNou,
            objectiuNou, nivellActivitatNou ->

            nom = nomNou
            email = emailNou
            dataNaixement = dataNaixementNova
            genere = genereNou
            alcada = alcadaNova
            pes = pesNou
            pesObjectiu = pesObjectiuNou
            objectiu = objectiuNou
            nivellActivitat = nivellActivitatNou

            editant = false
        }

    } else {

        ProfileScreen(
            nom,
            email,
            dataNaixement,
            genere,
            alcada,
            pes,
            objectiu,
            nivellActivitat
        ) {
            editant = true
        }
                }

                1 -> WeightTrackingScreen(pes, pesObjectiu, alcada)

                2 -> RutineScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessPreview() {
    AppFitnessTheme {
        FitnessApp()
    }
}
