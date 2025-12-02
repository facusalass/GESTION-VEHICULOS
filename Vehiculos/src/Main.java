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
        System.out.println("\n--- ALTA DE VEHÍCULO ---");

        String pat = leerTexto("Patente: ").toUpperCase();
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");

        int km = leerEntero("KM: ");

        String tipo = leerTexto("Ingrese Tipo de Vehículo (A para Auto, M para Moto): ").toUpperCase();

        Vehiculo v = null;

        if (tipo.equals("A")) {
            int puertas = leerEntero("Cantidad de Puertas: ");
            v = new Auto(pat, marca, modelo, km, puertas);

        } else if (tipo.equals("M")) {
            int cilindrada = leerEntero("Cilindrada: ");
            v = new Moto(pat, marca, modelo, km, cilindrada);

        } else {
            System.out.println("Tipo de vehículo no válido. Cancelando operación.");
            return;
        }

        if (sistema.agregarVehiculo(v)) {
            System.out.println("Vehículo agregado correctamente.");
        } else {
            System.out.println("Error: Ya existe esa patente.");
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

            Mostrable mostrar = () -> System.out.println(resultado.get());

            mostrar.mostrarResumen();

            System.out.println("-----------------------------");
        } else {
            System.out.println(" No existe vehículo con esa patente.");
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
            System.out.println("Mantenimiento registrado con éxito.");

        } catch (ElementoNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
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
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) {
                System.out.println("Debe ingresar un número. Intente otra vez.");
                continue;
            }
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Ingrese un número.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("⚠Error: El campo no puede estar vacío. Intente nuevamente.");
            } else {
                return input;
            }
        }
    }
}