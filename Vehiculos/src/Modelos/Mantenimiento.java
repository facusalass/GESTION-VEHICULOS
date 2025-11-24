package Modelos;
import java.time.LocalDate;

public class Mantenimiento {
    private String descripcion;
    private Double costo;
    private LocalDate fecha;

    public Mantenimiento(String descripcion, Double costo) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha = LocalDate.now();
    }

    @Override
    public String toString() {
        return "[Fecha: " + fecha + "] " + descripcion + " ($" + costo + ")";
    }

    public Double getCosto() { return costo; }
}