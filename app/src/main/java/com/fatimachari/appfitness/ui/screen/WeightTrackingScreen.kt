package com.fatimachari.appfitness.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatimachari.appfitness.Data.WeightTracking
import com.fatimachari.appfitness.ui.theme.AppFitnessTheme
import com.fatimachari.appfitness.tools.CalcularIMC
import com.fatimachari.appfitness.ui.Components.WeightTrackingItems

@Composable
fun WeightTrackingScreen(
    pesActual : Double,
    pesObjectiu :  Double,
    alçada : Int

) {
    val TAG = "WeightTrackingScreen"
    val WeightTrackingList = remember { mutableStateListOf<WeightTracking>() }
    var nouPesText by remember { mutableStateOf(pesActual.toString()) }
    var pesActual by remember  { mutableStateOf(pesActual) }

    Column(Modifier.fillMaxWidth()){
        Row(Modifier.padding(top = 16.dp)){
            Card(Modifier
                .weight(1f)
                .padding(8.dp)){
                Text("pes actual")
                Text("$pesActual kg")
            }
            Card(Modifier
                .weight(1f)
                .padding(8.dp)){
                Text("pes objectiu")
                Text("$pesObjectiu kg")
            }
            Card(Modifier
                .weight(1f)
                .padding(8.dp)){

                Text("IMC")
                var imc = CalcularIMC.calcularIMC(pesActual, alçada)
                Text(imc.toString())
            }
        }

        Text("Registra un nou pes", modifier = Modifier.padding(16.dp))
        OutlinedTextField( value = nouPesText.toString(),
            onValueChange = { nouPesText = it },
            label = { Text("Nou pes (kg)") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Button( onClick = {
            val pesDouble = nouPesText.toDoubleOrNull()
            if(pesDouble != null && pesDouble > 0.0) {
                var data = System.currentTimeMillis()
                var weightTracking = WeightTracking(data, pesDouble)
                WeightTrackingList.add(WeightTracking(data, pesDouble))
                //Actualitzo el pes
                pesActual = pesDouble
                Log.i(TAG, "Object weightTracking: $weightTracking")
                Log.i(TAG, "List weightTracking: $WeightTrackingList")
            }

        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)){
            Text("Guardar pes")
        }

        Text("Evolució del pes ", modifier = Modifier.padding(start = 16.dp, end = 16.dp,top = 32.dp, bottom = 16.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp,))

        Text ("Últims registres" , modifier = Modifier.padding(start = 16.dp, end = 16.dp,top = 32.dp, bottom = 16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            items (
                items=  WeightTrackingList,
                key = {weightTracking -> weightTracking.data}

            ){
                    weightTracking -> WeightTrackingItems(weightTracking) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeightTrackingScreenPreview(){
    AppFitnessTheme {
        WeightTrackingScreen(
            pesActual = 75.0,
            pesObjectiu = 70.0,
            alçada = 175

        )
    }
}