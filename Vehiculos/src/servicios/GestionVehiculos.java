package servicios;

import Modelos.Vehiculo;
import Modelos.Mantenimiento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionVehiculos {
    // Lista interna para guardar los vehículos en memoria
    private List<Vehiculo> vehiculos = new ArrayList<>();

    // Método que usa tu Main: agregarVehiculo
    public boolean agregarVehiculo(Vehiculo v) {

        if (buscarVehiculo(v.getPatente()).isPresent()) {
            return false; // Ya existe
        }
        vehiculos.add(v);
        return true;
    }

    // Método que usa tu Main: listarVehiculos
    public List<Vehiculo> listarVehiculos() {
        return vehiculos;
    }

    // Método que usa tu Main: buscarVehiculo (Devuelve Optional)
    public Optional<Vehiculo> buscarVehiculo(String patente) {
        return vehiculos.stream()
                .filter(v -> v.getPatente().equalsIgnoreCase(patente))
                .findFirst();
    }

    // Método que usa tu Main: registrarMantenimiento
    public boolean registrarMantenimiento(String patente, Mantenimiento m) {
        Optional<Vehiculo> v = buscarVehiculo(patente);
        if (v.isPresent()) {
            v.get().agregarMantenimiento(m);
            return true;
        }
        return false;
    }
}