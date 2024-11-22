package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Dieta {
    private List<String> platos;
    private List<String> ideasIA;
    private int totalKcal;
    private int totalProteinas;
    private int totalCarbohidratos;
    private int totalGrasas;

    // Constructor
    public Dieta() {
        this.platos = new ArrayList<>();
        this.ideasIA = new ArrayList<>();
        this.totalKcal = 0;
        this.totalProteinas = 0;
        this.totalCarbohidratos = 0;
        this.totalGrasas = 0;
    }

    // Métodos para manejar los platos y las ideas generadas por IA
    public void agregarPlato(String plato, int kcal, int proteinas, int carbohidratos, int grasas) {
        this.platos.add(plato);
        this.totalKcal += kcal;
        this.totalProteinas += proteinas;
        this.totalCarbohidratos += carbohidratos;
        this.totalGrasas += grasas;
    }

    public void agregarIdeaIA(String idea) {
        this.ideasIA.add(idea);
    }

    // Getters para los totales
    public int getTotalKcal() {
        return totalKcal;
    }

    public int getTotalProteinas() {
        return totalProteinas;
    }

    public int getTotalCarbohidratos() {
        return totalCarbohidratos;
    }

    public int getTotalGrasas() {
        return totalGrasas;
    }
}
