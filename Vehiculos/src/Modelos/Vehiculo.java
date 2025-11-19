package Modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private Integer kilometraje;

    private List<Mantenimiento> historialMantenimientos;

    // Constructor
    public Vehiculo(String patente, String marca, String modelo, Integer kilometraje) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.historialMantenimientos = new ArrayList<>();
    }

    public abstract void realizarServicioBasico();

    public void agregarMantenimiento(Mantenimiento m) {
        this.historialMantenimientos.add(m);
    }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getKilometraje() { return kilometraje; }
    public void setKilometraje(Integer kilometraje) { this.kilometraje = kilometraje; }
    public List<Mantenimiento> getHistorialMantenimientos() { return historialMantenimientos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(patente, vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }

    @Override
    public String toString() {
        return "Patente: " + patente + " | Marca: " + marca + " | Km: " + kilometraje;
    }
}