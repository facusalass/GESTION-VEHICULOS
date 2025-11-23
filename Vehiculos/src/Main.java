
import Modelos.*;
import servicios.GestionVehiculos;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final GestionVehiculos sistema = new GestionVehiculos();


    private static final String[] historialAcciones = new String[5];
    private static int contadorHistorial = 0;

    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Gestión...");

        mostrarMenu();
    }


    private static void mostrarMenu() {
        System.out.println("\n===== MENU PRINCIPAL (Integrante 3) =====");
        System.out.println("1. Agregar Vehículo");
        System.out.println("2. Listar Vehículos");
        System.out.println("3. Buscar Vehículo");
        System.out.println("4. Registrar Mantenimiento");
        System.out.println("5. Ver Historial de Sesión (Uso de Array)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        String input = scanner.nextLine();


        if (!esNumero(input)) {
            System.out.println(" Error: Ingrese un número válido.");
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
                System.out.println("Saliendo del sistema...");
                return;
            default:
                System.out.println(" Opción no válida.");
        }


        if (opcion != 0) {
            guardarEnArray("Usuario seleccionó opción " + opcion);
            mostrarMenu();
        }
    }



    private static void agregarVehiculo() {
        System.out.println("\n--- Agregar Vehículo ---");

        String patente;
        do {
            System.out.print("Ingrese patente (mínimo 6 caracteres): ");
            patente = scanner.nextLine().toUpperCase();
        } while (patente.length() < 6);

        System.out.print("Ingrese marca: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ingrese kilometraje: ");
        int km = Integer.parseInt(scanner.nextLine());

        System.out.print("Tipo (A)uto o (M)oto: ");
        String tipo = scanner.nextLine().toUpperCase();

        Vehiculo v = null;

        if (tipo.equals("A")) {
            System.out.print("Cantidad de puertas: ");
            int puertas = Integer.parseInt(scanner.nextLine());
            v = new Auto(patente, marca, modelo, km, puertas);
        } else {
            System.out.print("Cilindrada: ");
            int cc = Integer.parseInt(scanner.nextLine());
            v = new Moto(patente, marca, modelo, km, cc);
        }

        if (sistema.agregarVehiculo(v)) {
            System.out.println(" Vehículo agregado.");
        } else {
            System.out.println(" Error: Patente duplicada.");
        }
    }

    private static void listarVehiculos() {
        System.out.println("\n--- Listado ---");

        if (sistema.listarVehiculos().isEmpty()) {
            System.out.println("No hay datos.");
        } else {
            for (Vehiculo v : sistema.listarVehiculos()) {
                System.out.println(v);
            }
        }
    }

    private static void buscarVehiculo() {
        System.out.print("Ingrese patente a buscar: ");
        String pat = scanner.nextLine().toUpperCase();

        var encontrado = sistema.buscarVehiculo(pat);
        if (encontrado.isPresent()) {
            System.out.println("Encontrado: " + encontrado.get());
        } else {
            System.out.println("No existe.");
        }
    }

    private static void registrarMantenimiento() {
        System.out.print("Ingrese patente: ");
        String pat = scanner.nextLine().toUpperCase();
        System.out.print("Descripción servicio: ");
        String desc = scanner.nextLine();
        System.out.print("Costo: ");
        double costo = Double.parseDouble(scanner.nextLine());

        Mantenimiento m = new Mantenimiento(desc, costo);
        if (sistema.registrarMantenimiento(pat, m)) {
            System.out.println(" Mantenimiento registrado.");
        } else {
            System.out.println(" Vehículo no encontrado.");
        }
    }


    private static void guardarEnArray(String accion) {

        historialAcciones[contadorHistorial % 5] = accion;
        contadorHistorial++;
    }

    private static void mostrarHistorialArray() {
        System.out.println("\n--- Últimas 5 acciones (Array) ---");
        for (int i = 0; i < historialAcciones.length; i++) {
            if (historialAcciones[i] != null) {
                System.out.println("[" + (i+1) + "] " + historialAcciones[i]);
            }
        }
    }

    private static boolean esNumero(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}