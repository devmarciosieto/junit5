package br.com.mmmsieto;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private int contador = 0;
    private static int contador2 = 0;

    Calculadora calculadora = new Calculadora();

    @BeforeEach
    void setUp() {
        System.out.println("---> @BeforeEach");
    }

    @AfterEach
    void tearDown() {
        System.out.println("---> @AfterEach");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("---> @BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("---> @AfterAll");
    }
    @Test
    void somar() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        assertEquals(2, calculadora.somar(1, 1));
        assertEquals(3, calculadora.somar(1, 2));
        assertTrue( calculadora.somar(3, 1) == 4);
        assertFalse( calculadora.somar(3, 1) == 5);
    }

    @Test
    void dividir() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        assertEquals(2, calculadora.dividir(4, 2));
        assertEquals(3, calculadora.dividir(9, 3));
        assertTrue( calculadora.dividir(9, 3) == 3);
        assertFalse( calculadora.dividir(9, 3) == 4);
    }

    @Test
    void deveRetornarNumeroInteiroNaDivisao() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        assertEquals(2, calculadora.dividir(4, 2));
    }

    @Test
    void deveRetornarNumeroNegativosNaDivisao() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        assertEquals(-2, calculadora.dividir(-4, 2));
    }

    @Test
    void deveRetornarNumeroDecimalNaDivisao() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        assertNotEquals(3.33, calculadora.dividir(10, 3));
        assertEquals(3.33, calculadora.dividir(10, 3), 0.01);
    }

    @Test
    void deveRetornarNumeroZeroComNumeroZeroNaDivisao() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        assertEquals(0, calculadora.dividir(0, 3));
    }

    @Test
    void deveLancarExcecaoQaundoDividirPorZero_Junit4() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        try {
            calculadora.dividir02(3, 0);
            fail("Não lançou exceção");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }

    }

    @Test
    void deveLancarExcecaoQaundoDividirPorZero_Junit5() {
        System.out.println("contador " + ++contador);
        System.out.println("contador2 static " + ++contador2);
        ArithmeticException exception =
                assertThrows(ArithmeticException.class, () -> calculadora.dividir02(4, 0));

        assertEquals("/ by zero", exception.getMessage());
    }

}
