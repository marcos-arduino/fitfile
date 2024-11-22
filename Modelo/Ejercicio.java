package Modelo;

public class Ejercicio {
    private String nombre;
    private int duracion; // Duración en minutos
    private int caloriasQuemadas;

    // Constructor
    public Ejercicio(String nombre, int duracion, int caloriasQuemadas) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.caloriasQuemadas = caloriasQuemadas;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public void setCaloriasQuemadas(int caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }
}
