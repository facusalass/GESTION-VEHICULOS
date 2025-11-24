import Modelos.*;
import servicios.GestionVehiculos;
import Excepciones.ElementoNoEncontradoException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GestionVehiculos sistema = new GestionVehiculos();

    private static final String[] historialAcciones = new String[5];
    private static int contadorHistorial = 0;

    public static void main(String[] args) {
        System.out.println("Iniciando Sistema...");
        mostrarMenu();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Agregar Vehículo");
        System.out.println("2. Listar Vehículos");
        System.out.println("3. Buscar Vehículo");
        System.out.println("4. Registrar Mantenimiento");
        System.out.println("5. Ver Historial");
        System.out.println("0. Salir");
        System.out.print("Opción: ");

        String input = scanner.nextLine();

        if (input == null || !input.matches("\\d+")) {
            System.out.println("Por favor ingrese un número válido.");
            mostrarMenu();
            return;
        }

        int opcion = Integer.parseInt(input);

        switch (opcion) {
            case 1:
                agregarVehiculo();
                break;
            case 2:
                listarVehiculos();
                break;
            case 3:
                buscarVehiculo();
                break;
            case 4:
                registrarMantenimiento();
                break;
            case 5:
                mostrarHistorialArray();
                break;
            case 0:
                System.out.println("Saliendo...");
                return;
            default:
                System.out.println("Opción no válida.");
        }

        if (opcion != 0) {
            guardarEnArray("Opción " + opcion);
            mostrarMenu();
        }
    }

    private static void agregarVehiculo() {
        System.out.print("Patente: ");
        String pat = scanner.nextLine().toUpperCase();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String mod = scanner.nextLine();

        int km = 0;
        try {
            System.out.print("KM: ");
            km = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            km = 0;
        }


        System.out.print("Ingrese Tipo de Vehículo (A para Auto, M para Moto): ");
        String tipo = scanner.nextLine().toUpperCase();

        Vehiculo v;
        try {
            if (tipo.equals("A")) {

                v = new Auto(pat, marca, mod, km, 4);
            } else {
                System.out.print("Cilindrada: ");
                int c = Integer.parseInt(scanner.nextLine());
                v = new Moto(pat, marca, mod, km, c);
            }

            if (sistema.agregarVehiculo(v)) {
                System.out.println("✅ Vehículo agregado correctamente.");
            } else {
                System.out.println("⚠ Error: Ya existe esa patente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese números válidos.");
        }
    }

    private static void listarVehiculos() {
        if (sistema.listarVehiculos().isEmpty()) {
            System.out.println("Lista vacía.");
        } else {
            for (Vehiculo v : sistema.listarVehiculos()) {
                System.out.println(v);
            }
        }
    }

    private static void buscarVehiculo() {
        System.out.print("Ingrese patente a buscar: ");
        String pat = scanner.nextLine();

        var resultado = sistema.buscarVehiculo(pat);

        if (resultado.isPresent()) {
            System.out.println("-----------------------------");
            System.out.println("VEHÍCULO ENCONTRADO:");
            System.out.println(resultado.get());
            System.out.println("-----------------------------");
        } else {
            System.out.println("❌ No existe vehículo con esa patente.");
        }
    }

    private static void registrarMantenimiento() {
        System.out.print("Patente: ");
        String pat = scanner.nextLine().toUpperCase();
        System.out.print("Detalle del servicio: ");
        String det = scanner.nextLine();

        double costo = 0;
        try {
            System.out.print("Costo: ");
            costo = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Costo inválido.");
            return;
        }

        try {
            sistema.registrarMantenimiento(pat, new Mantenimiento(det, costo));
            System.out.println("✅ Mantenimiento registrado con éxito.");

        } catch (ElementoNoEncontradoException e) {
            System.out.println("⛔ ERROR: " + e.getMessage());
        }
    }

    private static void guardarEnArray(String accion) {
        historialAcciones[contadorHistorial % 5] = accion;
        contadorHistorial++;
    }

    private static void mostrarHistorialArray() {

        System.out.println("\n--- Historial de opciones del usuario ---");
        for (String s : historialAcciones) {
            if (s != null) System.out.println(s);
        }
    }
}