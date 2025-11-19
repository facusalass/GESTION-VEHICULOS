package test;
import Modelos.*;

public class TestIntegrante1 {
    public static void main(String[] args) {
        Auto miAuto = new Auto("AB123CD", "Ford", "Fiesta", 50000, 5);
        Moto miMoto = new Moto("123JQK", "Honda", "Titan", 15000, 150);

        miAuto.realizarServicioBasico();
        miMoto.realizarServicioBasico();

        miAuto.agregarMantenimiento(new Mantenimiento("Cambio de Aceite", 15000.0));

        Mostrable resumenAuto = () -> {
            System.out.println("\n--- RESUMEN LAMBDA ---");
            System.out.println("Vehículo: " + miAuto.getPatente());
            System.out.println("Gastos en mantenimiento: $" +
                    miAuto.getHistorialMantenimientos().stream().mapToDouble(m -> m.getCosto()).sum());
        };

        resumenAuto.mostrarResumen();
    }
}