package com.fatimachari.appfitness.ui.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatimachari.appfitness.Data.WeightTracking
import com.fatimachari.appfitness.ui.theme.AppFitnessTheme

@Composable
fun  WeightTrackingItems(weightTracking: WeightTracking){
    Row(Modifier.fillMaxWidth().padding(8.dp)){
        Text(text = formatDate (weightTracking.data), modifier = Modifier.weight(1f))
        Text("${weightTracking.pes} kg")
    }
}

@Preview(showBackground = true)
@Composable

fun WeightTrackingItemPreview() {
    var weightTracking = WeightTracking(System.currentTimeMillis(), 75.0)

    AppFitnessTheme {
        WeightTrackingItems(weightTracking)
    }
}

