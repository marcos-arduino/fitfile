package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario con sus datos personales, rutinas y dieta.
 */
public class Usuario implements Serializable {
    private String nombre;
    private int edad;
    private double peso;
    private double altura;
    private String gustos;
    private List<Rutina> rutinas;
    private Dieta dieta;
    private List<UsuarioProgreso> progresos;

    public Usuario(String nombre, int edad, double peso, double altura, String gustos) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor que cero");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        if (altura <= 0) {
            throw new IllegalArgumentException("La altura debe ser mayor que cero");
        }
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.gustos = gustos;
        this.rutinas = new ArrayList<>();
        this.progresos = new ArrayList<>();
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }

    public void agregarRutina(Rutina rutina) {
        this.rutinas.add(rutina);
    }

    public void eliminarRutina(Rutina rutina) {
        this.rutinas.remove(rutina);
    }

    public List<Rutina> getRutinas() {
        return new ArrayList<>(this.rutinas);
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public Dieta getDieta() {
        return this.dieta;
    }

    public void agregarProgreso(UsuarioProgreso progreso) {
        this.progresos.add(progreso);
    }

    public List<UsuarioProgreso> getProgresos() {
        return new ArrayList<>(this.progresos);
    }

}
