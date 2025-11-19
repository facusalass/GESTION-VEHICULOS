package Modelos;
public class Auto extends Vehiculo {
    private int cantidadPuertas;

    public Auto(String patente, String marca, String modelo, Integer kilometraje, int cantidadPuertas) {
        super(patente, marca, modelo, kilometraje);
        this.cantidadPuertas = cantidadPuertas;
    }

    @Override
    public void realizarServicioBasico() {
        System.out.println("Realizando alineación y balanceo al Auto " + getMarca());
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Auto (" + cantidadPuertas + " puertas)";
    }
}