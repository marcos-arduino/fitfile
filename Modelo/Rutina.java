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

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Ejercicio> getEjercicios() {
        return new ArrayList<>(ejercicios);
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
