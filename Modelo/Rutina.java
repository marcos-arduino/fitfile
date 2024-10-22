package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rutina {
    private String nombre;
    private List<Ejercicio> ejercicios;
    private Date fechaInicio;
    private Date fechaFin;

    // Constructor
    public Rutina(String nombre, Date fechaInicio, Date fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ejercicios = new ArrayList<>();
    }

    // Métodos para manejar los ejercicios en la rutina
    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }

    // Otros getters y setters...
}
