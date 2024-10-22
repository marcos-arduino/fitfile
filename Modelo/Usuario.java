package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private int edad;
    private double peso;
    private double altura;
    private String gustos;
    private List<Rutina> rutinas;
    private Dieta dieta;

    // Constructor
    public Usuario(String nombre, int edad, double peso, double altura, String gustos) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.gustos = gustos;
        this.rutinas = new ArrayList<>();
    }

    // Métodos para obtener y actualizar datos del usuario
    public void agregarRutina(Rutina rutina) {
        this.rutinas.add(rutina);
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    // Otros getters y setters...
}
