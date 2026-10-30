package com.fatimachari.appfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fatimachari.appfitness.ui.screen.EditProfileScreen
import com.fatimachari.appfitness.ui.screen.ProfileScreen
import com.fatimachari.appfitness.ui.theme.AppFitnessTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppFitnessTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FitnessApp()
                }
            }
        }
    }
}

@Composable
fun FitnessApp(){
    var nom: String by remember { mutableStateOf(value = "Laia Soler") }
    var email: String by remember { mutableStateOf(value = "Laia.soler@gmail.com") }
    var dataNaixement: String by remember { mutableStateOf(value = "17/05/2001")}
    var genere: String by remember { mutableStateOf(value = "Dona")}
    var alçada: Int by remember { mutableStateOf(value = 160)}
    var pes:Double by remember { mutableStateOf(value = 55.5)}
    var objectiu:String by remember { mutableStateOf("Guanyar massa muscular") }
    var pesObjectiu: Double by remember { mutableStateOf(value = 0.0) }
    var nivellActivitat:String by remember {mutableStateOf(" Moderat")}
    var editant: Boolean by remember { mutableStateOf(value = true)}

    if(editant){
        EditProfileScreen(nom, email, dataNaixement, genere, alçada,
            pes, pesObjectiu,objectiu, nivellActivitat,{nomNou, emailNou, dataNaixementNova, genereNou, alçadaNou,pesNou, pesObjectiuNou, objectiuNou, nivellActivitatNou ->
                nom = nomNou
                email = emailNou
                dataNaixement = dataNaixementNova
                genere = genereNou
                alçada = alçadaNou
                pes = pesNou
                pesObjectiu = pesObjectiuNou
                objectiu = objectiuNou
                nivellActivitat = nivellActivitatNou
                editant = false
            })
    }else{
        ProfileScreen(nom, email, dataNaixement, genere, alçada,
            pes,objectiu, nivellActivitat,
            { editant = true })
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessPreview() {
    AppFitnessTheme {
      FitnessApp()
    }
}