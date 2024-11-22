package Modelo;

public class APICalculos {
    public static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    public static double calcularGrasaCorporal(double imc, int edad, boolean esHombre) {
        double factorGenero = esHombre ? 1.0 : 0.0;
        return (1.20 * imc) + (0.23 * edad) - (10.8 * factorGenero) - 5.4;
    }

    public static double calcularTMB(double peso, double altura, int edad, boolean esHombre) {
        if (esHombre) {
            return 66 + (13.75 * peso) + (5 * altura * 100) - (6.75 * edad);
        } else {
            return 655 + (9.56 * peso) + (1.85 * altura * 100) - (4.68 * edad);
        }
    }
}
