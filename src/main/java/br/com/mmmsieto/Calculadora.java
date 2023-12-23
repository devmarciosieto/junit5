package br.com.mmmsieto;

public class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        System.out.println(calculadora.somar(1, 1) == 2);
        System.out.println(calculadora.somar(1, 2) == 3);
        System.out.println(calculadora.somar(3, 1) == 4);
    }

}
