package com.fatimachari.appfitness.tools

import kotlin.math.round


class CalcularIMC {
    companion object {
        fun calcularIMC(pesActual: Double, alçada: Int): Double  {
            if(pesActual <= 0 || alçada == 0 ){
                return 0.0
            }
            var alçadaMetres = alçada.toDouble()/100

            return  round (pesActual / (alçadaMetres * alçadaMetres) * 100) /100
        }
    }

}