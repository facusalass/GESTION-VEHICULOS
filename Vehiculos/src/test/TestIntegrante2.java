package test;
import Modelos.*;

public class TestIntegrante2 {
    public static void main(String[] args) {

        GestorVehiculos gestor = new GestorVehiculos();


        Vehiculo auto1 = new Auto("ABC123", "Toyota", "Corolla", 2015, 120000);
        Vehiculo auto2 = new Auto("XYZ789", "Ford", "Fiesta", 2018, 90000);
        Vehiculo moto1 = new Moto("MOT456", "Honda", "CB190", 2020, 30000);

        System.out.println("\n=== AGREGANDO VEHÍCULOS ===");
        gestor.agregarVehiculo(auto1);
        gestor.agregarVehiculo(auto2);
        gestor.agregarVehiculo(moto1);


        System.out.println("\n=== LISTA DE VEHÍCULOS ===");
        gestor.listarVehiculos();


        System.out.println("\n=== BUSCAR VEHÍCULO ===");
        gestor.buscarPorPatente("ABC123").ifPresentOrElse(
                v -> System.out.println("Encontrado: " + v),
                () -> System.out.println("No encontrado")
        );


        System.out.println("\n=== MODIFICANDO KM ===");
        gestor.modificarKilometraje("XYZ789", 95000);


        System.out.println("\n=== REGISTRAR MANTENIMIENTO ===");
        Mantenimiento m1 = new Mantenimiento("Cambio de aceite", 15000.0);
        Mantenimiento m2 = new Mantenimiento("Frenos", 28000.0);

        gestor.registrarMantenimiento("ABC123", m1);
        gestor.registrarMantenimiento("XYZ789", m2);


        System.out.println("\n=== MANTENIMIENTOS ABC123 ===");
        gestor.listarMantenimientos("ABC123");

        System.out.println("\n=== MANTENIMIENTOS XYZ789 ===");
        gestor.listarMantenimientos("XYZ789");


        System.out.println("\n=== COSTO TOTAL ===");
        System.out.println("ABC123: $" + gestor.calcularCostoTotalMantenimientos("ABC123"));
        System.out.println("XYZ789: $" + gestor.calcularCostoTotalMantenimientos("XYZ789"));


        System.out.println("\n=== ORDEN POR KM DESC ===");
        gestor.ordenarPorKilometrajeDesc();
        gestor.listarVehiculos();


        System.out.println("\n=== FILTRAR POR MARCA (Toyota) ===");
        gestor.filtrarPorMarca("Toyota").forEach(System.out::println);


        System.out.println("\n=== HISTORIAL DE OPERACIONES ===");
        gestor.mostrarHistorialOperaciones();
    }
}
