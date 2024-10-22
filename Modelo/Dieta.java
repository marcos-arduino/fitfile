package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Dieta {
    private List<String> platos;
    private List<String> ideasIA;

    // Constructor
    public Dieta() {
        this.platos = new ArrayList<>();
        this.ideasIA = new ArrayList<>();
    }

    // Métodos para manejar los platos y las ideas generadas por IA
    public void agregarPlato(String plato) {
        this.platos.add(plato);
    }

    public void agregarIdeaIA(String idea) {
        this.ideasIA.add(idea);
    }

    // Otros getters y setters...
}
