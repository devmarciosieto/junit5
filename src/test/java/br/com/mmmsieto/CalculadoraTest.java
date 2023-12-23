package br.com.mmmsieto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void somar() {
        Calculadora calculadora = new Calculadora();
        assertEquals(2, calculadora.somar(1, 1));
        assertEquals(3, calculadora.somar(1, 2));
        assertEquals(4, calculadora.somar(3, 1));
    }

}