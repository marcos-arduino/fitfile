package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa el progreso de un usuario.
 */
public class UsuarioProgreso implements Serializable {
    private Date fecha;
    private double peso;
    private double porcentajeGrasa;
    private double masaMuscular;

    public UsuarioProgreso(Date fecha, double peso, double porcentajeGrasa, double masaMuscular) {
        this.fecha = fecha;
        this.peso = peso;
        this.porcentajeGrasa = porcentajeGrasa;
        this.masaMuscular = masaMuscular;
    }

    // Getters y setters
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(double porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public double getMasaMuscular() {
        return masaMuscular;
    }

    public void setMasaMuscular(double masaMuscular) {
        this.masaMuscular = masaMuscular;
    }
}
