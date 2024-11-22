package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario con sus datos personales, rutinas y dieta.
 */
public class Usuario implements Serializable {
    private int id;  // Atributo id
    private String nombre;
    private int edad;
    private double peso;
    private double altura;
    private String gustos;
    private List<Rutina> rutinas;
    private Dieta dieta;  // Atributo dieta
    private List<UsuarioProgreso> progresos;

    // Constructor que acepta dieta
    public Usuario(String nombre, int edad, double peso, double altura, String gustos, Dieta dieta) {
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
        this.dieta = dieta;  // Asigna la dieta directamente
    }

    // Constructor sin dieta (asigna una nueva dieta por defecto)
    public Usuario(String nombre, int edad, double peso, double altura, String gustos) {
        this(nombre, edad, peso, altura, gustos, new Dieta());
    }

    // Getters y Setters
    public int getId() {
        return id;  // Getter para id
    }

    public void setId(int id) {
        this.id = id;  // Setter para id
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }

    public List<Rutina> getRutinas() {
        return new ArrayList<>(this.rutinas);
    }

    public Dieta getDieta() {
        return this.dieta;  // Getter para dieta
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;  // Setter para dieta
    }

    public List<UsuarioProgreso> getProgresos() {
        return new ArrayList<>(this.progresos);
    }

    public void agregarRutina(Rutina rutina) {
        this.rutinas.add(rutina);
    }

    public void eliminarRutina(Rutina rutina) {
        this.rutinas.remove(rutina);
    }

    public void agregarProgreso(UsuarioProgreso progreso) {
        this.progresos.add(progreso);
    }
}