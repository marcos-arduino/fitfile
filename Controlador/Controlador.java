package Controlador;

import Modelo.Usuario;
import Modelo.Rutina;
import Modelo.Dieta;


public class Controlador {
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrarUsuario(String nombre, int edad, double peso, double altura, String gustos) {
        usuario = new Usuario(nombre, edad, peso, altura, gustos);
    }

    public void agregarRutina(String nombre, Date fechaInicio, Date fechaFin) {
        Rutina rutina = new Rutina(nombre, fechaInicio, fechaFin);
        usuario.agregarRutina(rutina);
    }

    public void agregarDieta(String plato) {
        Dieta dieta = new Dieta();
        dieta.agregarPlato(plato);
        usuario.setDieta(dieta);
    }

    // Otros métodos para la lógica de negocio...
}
