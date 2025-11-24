package Modelos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GestorVehiculos {

    private List<Vehiculo> vehiculos;
    private ListaOperaciones historial = new ListaOperaciones();

    public GestorVehiculos() {
        this.vehiculos = new ArrayList<>();
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {

        boolean existe = vehiculos.stream()
                .anyMatch(v -> v.getPatente().equalsIgnoreCase(vehiculo.getPatente()));

        if (existe) return false;

        boolean ok = vehiculos.add(vehiculo);

        if (ok) {
            historial.agregar("Se agregó el vehículo " + vehiculo.getPatente());
        }

        return ok;
    }

    public Optional<Vehiculo> buscarPorPatente(String patente) {
        return vehiculos.stream()
                .filter(v -> v.getPatente().equalsIgnoreCase(patente))
                .findFirst();
    }

    public boolean eliminarVehiculo(String patente) {
        boolean eliminado = vehiculos.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));

        if (eliminado) {
            historial.agregar("Se eliminó el vehículo " + patente);
        }

        return eliminado;
    }

    public boolean modificarKilometraje(String patente, Integer nuevoKm) {
        Optional<Vehiculo> opt = buscarPorPatente(patente);

        if (opt.isPresent()) {
            opt.get().setKilometraje(nuevoKm);
            historial.agregar("Se modificó el kilometraje del vehículo " + patente + " a " + nuevoKm);
            return true;
        }

        return false;
    }

    public void listarVehiculos() {
        vehiculos.forEach(System.out::println);
    }

    public List<Vehiculo> filtrarPorMarca(String marca) {
        return vehiculos.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .toList();
    }

    public void ordenarPorKilometrajeDesc() {
        vehiculos.sort(
                Comparator.comparing(Vehiculo::getKilometraje).reversed()
        );
    }


    public boolean registrarMantenimiento(String patente, Mantenimiento mantenimiento) {
        Optional<Vehiculo> opt = buscarPorPatente(patente);

        if (opt.isEmpty()) return false;

        Vehiculo v = opt.get();
        v.agregarMantenimiento(mantenimiento);

        historial.agregar("Se registró un mantenimiento en " + patente);

        return true;
    }

    public void listarMantenimientos(String patente) {
        buscarPorPatente(patente).ifPresent(v -> {
            System.out.println("Mantenimientos de " + v.getPatente());
            v.getHistorialMantenimientos().forEach(System.out::println);
        });
    }

    public double calcularCostoTotalMantenimientos(String patente) {
        return buscarPorPatente(patente)
                .map(v -> v.getHistorialMantenimientos().stream()
                        .mapToDouble(Mantenimiento::getCosto)
                        .sum())
                .orElse(0.0);
    }

    public void mostrarHistorialOperaciones() {
        historial.mostrarHistorial();
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}
