package servicios;

import Modelos.Vehiculo;
import Modelos.Mantenimiento;
import Excepciones.ElementoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionVehiculos {


    private List<Vehiculo> vehiculos = new ArrayList<>();


    public boolean agregarVehiculo(Vehiculo v) {

        if (buscarVehiculo(v.getPatente()).isPresent()) {
            return false;
        }
        vehiculos.add(v);
        return true;
    }


    public List<Vehiculo> listarVehiculos() {
        return vehiculos;
    }


    public Optional<Vehiculo> buscarVehiculo(String patente) {
        return vehiculos.stream()
                .filter(v -> v.getPatente().equalsIgnoreCase(patente))
                .findFirst();
    }



    public void registrarMantenimiento(String patente, Mantenimiento m) throws ElementoNoEncontradoException {
        Optional<Vehiculo> v = buscarVehiculo(patente);

        if (v.isPresent()) {
            v.get().agregarMantenimiento(m);
        } else {

            throw new ElementoNoEncontradoException("No se puede registrar mantenimiento: Patente " + patente + " no encontrada.");
        }
    }
}