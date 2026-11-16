package com.fatimachari.appfitness

import com.fatimachari.appfitness.tools.CalcularIMC
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalcularIMCTest {
@Test
    fun calcularIMCPesNormal(){
        var pesActual = 75.0
        var alçada = 175
        var imc = CalcularIMC.calcularIMC(pesActual, alçada)
        assertEquals(24.49, imc, 0.1)
    }

    @Test
    fun calcularIMCPesCero(){
        var pesActual = -1.0
        var alçada = 175
        var imc = CalcularIMC.calcularIMC(pesActual, alçada)
        assertEquals(0.0, imc, 0.1)

    }
    @Test
    fun calcularIMCAlçadaInferiorCero(){
        var pesActual = 75.0
        var alçada = 0
        var imc = CalcularIMC.calcularIMC(pesActual, alçada)
        assertEquals(0.0, imc, 0.1)

    }
}