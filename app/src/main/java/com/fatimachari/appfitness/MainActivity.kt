
package com.fatimachari.appfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.fatimachari.appfitness.ui.theme.AppFitnessTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppFitnessTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        FitnessApp()
                    }
                }
            }
        }
    }
}

@Composable
fun FitnessApp() {

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

    if (editant) {

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
}

@Preview(showBackground = true)
@Composable
fun FitnessPreview() {
    AppFitnessTheme {
        FitnessApp()
    }
}
